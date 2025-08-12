package com.saborhub.infrastructure.gateways;

import com.saborhub.application.gateways.RepositorioUsuario;
import com.saborhub.domain.entities.usuario.RegistroUsuarioDto;
import com.saborhub.domain.entities.usuario.Usuario;
import com.saborhub.infrastructure.persistence.UsuarioEntity;
import com.saborhub.infrastructure.persistence.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;

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
    public void save(Usuario usuario) {
        UsuarioEntity usuarioEntity = mapper.toEntity(usuario);
        repositorio.save(usuarioEntity);
    }

    @Override
    public Usuario findByLogin(String login) {
        UserDetails userDetails = repositorio.findByLogin(login);
        if (userDetails == null) {
            return null;
        }
        return mapper.toDomain((UsuarioEntity) userDetails);
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
    public Usuario obterUsuario(String id) {
        return repositorio.findById(id)
                .map(mapper::toDomain)
                .orElse(null);  
    }

    @Override
    public void deletarPorId(String id) {
        repositorio.deleteById(id);
    }
}