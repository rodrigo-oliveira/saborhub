package com.saborhub.infrastructure.controller;

import com.saborhub.application.usecases.ListarUsuarios;
import com.saborhub.application.usecases.ObterUsuario;
import com.saborhub.domain.entities.usuario.Usuario;
import com.saborhub.domain.entities.usuario.UsuarioDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final ListarUsuarios listarUsuarios;
    private final ObterUsuario obterUsuario;

    public UsuarioController(ListarUsuarios listarUsuarios, ObterUsuario obterUsuario) {
        this.listarUsuarios = listarUsuarios;
        this.obterUsuario = obterUsuario;
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
}