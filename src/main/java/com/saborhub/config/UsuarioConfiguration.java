package com.saborhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.saborhub.application.gateways.RepositorioUsuario;
import com.saborhub.application.usecases.ListarUsuarios;
import com.saborhub.application.usecases.ObterUsuario;
import com.saborhub.infrastructure.gateways.RepositorioUsuarioJpa;
import com.saborhub.infrastructure.gateways.UsuarioEntityMapper;
import com.saborhub.infrastructure.persistence.UsuarioRepository;

@Configuration
public class UsuarioConfiguration {
    @Bean
    RepositorioUsuario criarRepositorioJpa(UsuarioRepository repositorio, UsuarioEntityMapper mapper){
        return new RepositorioUsuarioJpa(repositorio, mapper);
    }

    @Bean
    ListarUsuarios listarUsuarios(RepositorioUsuario repositorioDeUsuario){
        return new ListarUsuarios(repositorioDeUsuario);
    }

    @Bean
    ObterUsuario obterUsuario(RepositorioUsuario repositorioDeUsuario){
        return new ObterUsuario(repositorioDeUsuario);
    }

    @Bean
    UsuarioEntityMapper retornaMapper(){
        return new UsuarioEntityMapper();
    }
}