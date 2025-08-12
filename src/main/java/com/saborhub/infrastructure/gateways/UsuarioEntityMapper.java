package com.saborhub.infrastructure.gateways;

import com.saborhub.domain.entities.usuario.Usuario;
import com.saborhub.infrastructure.persistence.UsuarioEntity;

public class UsuarioEntityMapper {

    public UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getLogin(),
            usuario.getSenha(),
            usuario.getDataUltimaAlteracao(),
            usuario.getRole(),
            usuario.getEndereco()
        );
    }

    public Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
            entity.getId(),
            entity.getNome(),
            entity.getEmail(),
            entity.getLogin(),
            entity.getSenha(),
            entity.getDataUltimaAlteracao(),
            entity.getRole(),
            entity.getEndereco()
        );
    }
}