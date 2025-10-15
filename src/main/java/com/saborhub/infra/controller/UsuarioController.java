package com.saborhub.infra.controller;

import com.saborhub.application.dto.AlterarSenhaDto;
import com.saborhub.application.dto.AtualizarUsuarioDto;
import com.saborhub.application.dto.RegistroUsuarioDto;
import com.saborhub.application.dto.UsuarioDto;
import com.saborhub.application.usecases.DeletarUsuarioUseCase;
import com.saborhub.application.usecases.ListarUsuariosUseCase;
import com.saborhub.application.usecases.ObterUsuarioUseCase;
import com.saborhub.application.usecases.RegistrarUsuarioUseCase;
import com.saborhub.domain.entities.Usuario;
import com.saborhub.infra.persistence.UsuarioEntity;
import com.saborhub.infra.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final ListarUsuariosUseCase listarUsuarios;
    private final ObterUsuarioUseCase obterUsuario;
    private final DeletarUsuarioUseCase deletarUsuario;
    private final RegistrarUsuarioUseCase registrarUsuario;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(
            ListarUsuariosUseCase listarUsuarios,
            ObterUsuarioUseCase obterUsuario,
            DeletarUsuarioUseCase deletarUsuario,
            RegistrarUsuarioUseCase registrarUsuario,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.listarUsuarios = listarUsuarios;
        this.obterUsuario = obterUsuario;
        this.deletarUsuario = deletarUsuario;
        this.registrarUsuario = registrarUsuario;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Void> registrarUsuario(@RequestBody RegistroUsuarioDto data) {
        registrarUsuario.executar(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<UsuarioDto> listarUsuarios() {
        return listarUsuarios
                .obterTodosUsuarios()
                .stream()
                .map(usuario -> new UsuarioDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getLogin(),
                        usuario.getDataUltimaAlteracao(),
                        usuario.getPerfil(),
                        usuario.getEndereco()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UsuarioDto obterUsuario(@PathVariable String id) {
        Usuario usuario = obterUsuario.obterPeloId(id);

        return new UsuarioDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getDataUltimaAlteracao(),
                usuario.getPerfil(),
                usuario.getEndereco()
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        deletarUsuario.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @PreAuthorize("hasRole('CLIENTE') or hasRole('RESTAURANTE') or hasRole('ADMIN')")
    public ResponseEntity<UsuarioDto> atualizarUsuario(
            @AuthenticationPrincipal UsuarioEntity autenticado,
            @RequestBody AtualizarUsuarioDto payload
    ) {
        if (payload.nome() != null && !payload.nome().isBlank()) {
            autenticado.setNome(payload.nome());
        }
        if (payload.email() != null && !payload.email().isBlank()) {
            autenticado.setEmail(payload.email());
        }
        if (payload.login() != null && !payload.login().isBlank()) {
            autenticado.setLogin(payload.login());
        }
        if (payload.endereco() != null) {
            autenticado.setEndereco(payload.endereco());
        }

        autenticado.touchUltimaAlteracao();
        UsuarioEntity salvo = usuarioRepository.save(autenticado);

        UsuarioDto body = new UsuarioDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getLogin(),
                salvo.getDataUltimaAlteracao(),
                salvo.getPerfil(),
                salvo.getEndereco()
        );

        return ResponseEntity.ok(body);
    }

    @PutMapping("/alterar-senha")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('RESTAURANTE') or hasRole('ADMIN')")
    public ResponseEntity<Void> alterarSenha(
            @AuthenticationPrincipal UsuarioEntity autenticado,
            @RequestBody AlterarSenhaDto payload
    ) {
        if (payload == null || payload.senhaAtual() == null || payload.novaSenha() == null
                || payload.senhaAtual().isBlank() || payload.novaSenha().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (!passwordEncoder.matches(payload.senhaAtual(), autenticado.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        autenticado.setSenha(passwordEncoder.encode(payload.novaSenha()));
        autenticado.touchUltimaAlteracao();
        usuarioRepository.save(autenticado);

        return ResponseEntity.noContent().build();
    }
}