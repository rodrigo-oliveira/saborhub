package com.saborhub.infra.gateways;

import com.saborhub.application.gateways.UsuarioRepositoryInterface;
import com.saborhub.domain.entities.Usuario;
import com.saborhub.infra.persistence.UsuarioEntity;
import com.saborhub.infra.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioEntityRepository implements UsuarioRepositoryInterface {
    private final UsuarioRepository repositorio;
    private final UsuarioEntityMapper mapper;

    public UsuarioEntityRepository(UsuarioRepository repositorio, UsuarioEntityMapper mapper) {
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
    public Usuario obterPeloId(String id) {
        return repositorio.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public void deletar(String id) {
        repositorio.deleteById(id);
    }
}