package com.saborhub.infra.gateways;

import com.saborhub.domain.entities.Restaurante;
import com.saborhub.infra.persistence.RestauranteEntity;

public class RestauranteEntityMapper {

    public RestauranteEntity toEntity(Restaurante restaurante) {
        RestauranteEntity entity = new RestauranteEntity();
        entity.setId(restaurante.getId());
        entity.setCnpj(restaurante.getCnpj());
        entity.setNome(restaurante.getNome());
        entity.setEndereco(restaurante.getEndereco());
        entity.setTipoCozinha(restaurante.getTipoCozinha());
        entity.setHorarioFuncionamento(restaurante.getHorarioFuncionamento());
        entity.setDonoId(restaurante.getDonoId());
        return entity;
    }

    public Restaurante toDomain(RestauranteEntity entity) {
        Restaurante restaurante = new Restaurante(
                entity.getCnpj(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getTipoCozinha(),
                entity.getHorarioFuncionamento(),
                entity.getDonoId()
        );
        restaurante.setId(entity.getId());
        return restaurante;
    }
}