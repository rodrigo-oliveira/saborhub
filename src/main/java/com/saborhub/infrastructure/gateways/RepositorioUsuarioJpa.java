package com.saborhub.infrastructure.gateways;

import com.saborhub.application.gateways.RepositorioUsuario;
import com.saborhub.domain.entities.usuario.Usuario;
import com.saborhub.infrastructure.persistence.UsuarioEntity;
import com.saborhub.infrastructure.persistence.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioUsuarioJpa implements RepositorioUsuario {
    private final UsuarioRepository repositorio;
    private final UsuarioEntityMapper mapper;

    public RepositorioUsuarioJpa(UsuarioRepository repositorio, UsuarioEntityMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        repositorio.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repositorio
                .findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Usuario obterUsuario(Long id) {
        return repositorio.findById(id)
                .map(mapper::toDomain)
                .orElse(null);  
    }
}