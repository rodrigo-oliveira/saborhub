package com.saborhub.infra.config;

import com.saborhub.application.gateways.UsuarioRepositoryInterface;
import com.saborhub.application.usecases.DeletarUsuario;
import com.saborhub.application.usecases.ListarUsuarios;
import com.saborhub.application.usecases.ObterUsuario;
import com.saborhub.application.usecases.RegistrarUsuario;
import com.saborhub.infra.gateways.UsuarioEntityMapper;
import com.saborhub.infra.gateways.UsuarioEntityRepository;
import com.saborhub.infra.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsuarioConfiguration {
    @Bean
    UsuarioRepositoryInterface criarRepositorioJpa(UsuarioRepository repository, UsuarioEntityMapper mapper) {
        return new UsuarioEntityRepository(repository, mapper);
    }

    @Bean
    ListarUsuarios listarUsuarios(UsuarioRepositoryInterface repository) {
        return new ListarUsuarios(repository);
    }

    @Bean
    ObterUsuario obterUsuario(UsuarioRepositoryInterface repository) {
        return new ObterUsuario(repository);
    }

    @Bean
    RegistrarUsuario registrarUsuario(UsuarioRepositoryInterface repository, PasswordEncoder passwordEncoder) {
        return new RegistrarUsuario(repository, passwordEncoder);
    }

    @Bean
    UsuarioEntityMapper retornaMapper() {
        return new UsuarioEntityMapper();
    }

    @Bean
    public DeletarUsuario deletarUsuario(UsuarioRepositoryInterface repository) {
        return new DeletarUsuario(repository);
    }
}