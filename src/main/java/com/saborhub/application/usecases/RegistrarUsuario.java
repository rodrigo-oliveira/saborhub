package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RepositorioUsuario;
import com.saborhub.domain.entities.usuario.RegistroUsuarioDto;
import com.saborhub.domain.entities.usuario.Usuario;

import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrarUsuario {

    private final RepositorioUsuario repositorioUsuario;
    private final PasswordEncoder passwordEncoder;

    public RegistrarUsuario(RepositorioUsuario repositorioUsuario, PasswordEncoder passwordEncoder) {
        this.repositorioUsuario = repositorioUsuario;
        this.passwordEncoder = passwordEncoder;
    }

    public void executar(RegistroUsuarioDto dados) {
        if (repositorioUsuario.findByLogin(dados.login()) != null) {
            throw new IllegalArgumentException("Usuário com este login já existe.");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        Usuario novoUsuario = new Usuario(
                dados.id(),
                dados.nome(),
                dados.email(),
                dados.login(),
                senhaCriptografada,
                java.time.ZonedDateTime.now(),
                dados.role(),
                dados.endereco()
        );

        repositorioUsuario.save(novoUsuario);
    }
}
