package com.saborhub.infrastructure.controller;

import com.saborhub.application.usecases.ListarUsuarios;
import com.saborhub.application.usecases.ObterUsuario;
import com.saborhub.application.usecases.DeletarUsuario;
import com.saborhub.domain.entities.usuario.AtualizarUsuarioDto;
import com.saborhub.domain.entities.usuario.RegistroUsuarioDto;
import com.saborhub.domain.entities.usuario.AlterarSenhaDto;
import com.saborhub.domain.entities.usuario.Usuario;
import com.saborhub.domain.entities.usuario.UsuarioDto;
import com.saborhub.infrastructure.persistence.UsuarioEntity;
import com.saborhub.infrastructure.persistence.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final ListarUsuarios listarUsuarios;
    private final ObterUsuario obterUsuario;
    private final DeletarUsuario deletarUsuario;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    public UsuarioController(
        ListarUsuarios listarUsuarios,
        ObterUsuario obterUsuario,
        DeletarUsuario deletarUsuario,
        UsuarioRepository usuarioRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.listarUsuarios = listarUsuarios;
        this.obterUsuario = obterUsuario;
        this.deletarUsuario = deletarUsuario;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Void> registrarUsuario(@RequestBody RegistroUsuarioDto data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        UsuarioEntity newUser = new UsuarioEntity(
            data.nome(),
            data.email(),
            data.login(),
            encryptedPassword,
            java.time.ZonedDateTime.now(),
            data.role(),
            data.endereco()
        );

        this.repository.save(newUser);

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
                        usuario.getRole(),
                        usuario.getEndereco()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UsuarioDto obterUsuario(@PathVariable String id) {
        Usuario usuario = obterUsuario.obterUsuarioPorId(id);

        return new UsuarioDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getDataUltimaAlteracao(),
                usuario.getRole(),
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
                salvo.getRole(),
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