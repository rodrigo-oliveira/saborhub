package com.saborhub.infra.config;

import com.saborhub.application.gateways.UsuarioRepositoryInterface;
import com.saborhub.application.usecases.DeletarUsuarioUseCase;
import com.saborhub.application.usecases.ListarUsuariosUseCase;
import com.saborhub.application.usecases.ObterUsuarioUseCase;
import com.saborhub.application.usecases.RegistrarUsuarioUseCase;
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
    ListarUsuariosUseCase listarUsuarios(UsuarioRepositoryInterface repository) {
        return new ListarUsuariosUseCase(repository);
    }

    @Bean
    ObterUsuarioUseCase obterUsuario(UsuarioRepositoryInterface repository) {
        return new ObterUsuarioUseCase(repository);
    }

    @Bean
    RegistrarUsuarioUseCase registrarUsuario(UsuarioRepositoryInterface repository, PasswordEncoder passwordEncoder) {
        return new RegistrarUsuarioUseCase(repository, passwordEncoder);
    }

    @Bean
    UsuarioEntityMapper retornaMapper() {
        return new UsuarioEntityMapper();
    }

    @Bean
    public DeletarUsuarioUseCase deletarUsuario(UsuarioRepositoryInterface repository) {
        return new DeletarUsuarioUseCase(repository);
    }
}