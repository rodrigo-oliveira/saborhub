package com.saborhub.infrastructure.controller;

import com.saborhub.application.usecases.ListarUsuarios;
import com.saborhub.application.usecases.ObterUsuario;
import com.saborhub.domain.entities.usuario.AtualizarUsuarioDto;
import com.saborhub.domain.entities.usuario.Usuario;
import com.saborhub.domain.entities.usuario.UsuarioDto;
import com.saborhub.infrastructure.persistence.UsuarioEntity;
import com.saborhub.infrastructure.persistence.UsuarioRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(
        ListarUsuarios listarUsuarios,
        ObterUsuario obterUsuario,
        UsuarioRepository usuarioRepository
    ) {
        this.listarUsuarios = listarUsuarios;
        this.obterUsuario = obterUsuario;
        this.usuarioRepository = usuarioRepository;
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

    @PutMapping("/alterar")
    public ResponseEntity<UsuarioDto> atualizarMeuCadastro(
            @AuthenticationPrincipal UsuarioEntity autenticado,
            @RequestBody AtualizarUsuarioDto payload
    ) {
        // Atualiza campos permitidos
        if (payload.nome() != null && !payload.nome().isBlank()) {
            autenticado.setNome(payload.nome());
        }
        if (payload.email() != null && !payload.email().isBlank()) {
            autenticado.setEmail(payload.email());
        }
        if (payload.login() != null && !payload.login().isBlank()) {
            // opcional: validar duplicidade de login
            autenticado.setLogin(payload.login());
        }
        if (payload.password() != null && !payload.password().isBlank()) {
            autenticado.setPassword(new BCryptPasswordEncoder().encode(payload.password()));
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
}