package com.saborhub.application.usecases;

import com.saborhub.application.dto.RegistroUsuarioDto;
import com.saborhub.application.gateways.UsuarioRepositoryInterface;
import com.saborhub.domain.entities.Usuario;
import com.saborhub.domain.exceptions.UsuarioJaExisteException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrarUsuarioUseCase {

    private final UsuarioRepositoryInterface repositorioUsuario;
    private final PasswordEncoder passwordEncoder;

    public RegistrarUsuarioUseCase(UsuarioRepositoryInterface repositorioUsuario, PasswordEncoder passwordEncoder) {
        this.repositorioUsuario = repositorioUsuario;
        this.passwordEncoder = passwordEncoder;
    }

    public void executar(RegistroUsuarioDto dados) {
        if (repositorioUsuario.findByLogin(dados.login()) != null) {
            throw new UsuarioJaExisteException("Usuário com este login já existe.");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        Usuario novoUsuario = new Usuario(
                dados.id(),
                dados.nome(),
                dados.email(),
                dados.login(),
                senhaCriptografada,
                java.time.ZonedDateTime.now(),
                dados.perfil(),
                dados.endereco()
        );

        repositorioUsuario.save(novoUsuario);
    }
}
