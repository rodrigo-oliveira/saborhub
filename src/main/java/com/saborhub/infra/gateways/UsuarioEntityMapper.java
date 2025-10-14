package com.saborhub.infra.gateways;

import com.saborhub.domain.entities.Usuario;
import com.saborhub.infra.persistence.UsuarioEntity;

public class UsuarioEntityMapper {

    public UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getDataUltimaAlteracao(),
                usuario.getPerfil(),
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
                entity.getPerfil(),
                entity.getEndereco()
        );
    }
}