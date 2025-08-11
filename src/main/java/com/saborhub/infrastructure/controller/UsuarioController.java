package com.saborhub.infrastructure.controller;

import com.saborhub.application.usecases.CriarUsuario;
import com.saborhub.application.usecases.ListarUsuarios;
import com.saborhub.application.usecases.ObterUsuario;
import com.saborhub.domain.entities.usuario.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final CriarUsuario criarUsuario;
    private final ListarUsuarios listarUsuarios;
    private final ObterUsuario obterUsuario;

    public UsuarioController(CriarUsuario criarUsuario, ListarUsuarios listarUsuarios, ObterUsuario obterUsuario) {
        this.criarUsuario = criarUsuario;
        this.listarUsuarios = listarUsuarios;
        this.obterUsuario = obterUsuario;
    }

    @PostMapping
    public UsuarioDto cadastrarUsuario(@RequestBody UsuarioDto dto) {
        Usuario salvo = criarUsuario.cadastrarUsuario(new Usuario(
                dto.id(),
                dto.nome(),
                dto.email(),
                dto.login(),
                dto.senha(),
                dto.dataUltimaAlteracao()));

        return new UsuarioDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getLogin(),
                salvo.getSenha(),
                salvo.getDataUltimaAlteracao());

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
                        usuario.getSenha(),
                        usuario.getDataUltimaAlteracao()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UsuarioDto obterUsuario(@PathVariable Long id) {
        Usuario usuario = obterUsuario.obterUsuarioPorId(id);

        return new UsuarioDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getDataUltimaAlteracao());
    }
}