package com.saborhub.infra.gateways;

import com.saborhub.domain.entities.ItemCardapio;
import com.saborhub.infra.persistence.ItemCardapioEntity;

import java.util.UUID;

public class ItemCardapioEntityMapper {

    public ItemCardapioEntity toEntity(ItemCardapio itemCardapio) {
        ItemCardapioEntity entity = new ItemCardapioEntity();
        if (itemCardapio.getId() != null) {
            entity.setId(itemCardapio.getId().toString());
        }
        entity.setNome(itemCardapio.getNome());
        entity.setDescricao(itemCardapio.getDescricao());
        entity.setPreco(itemCardapio.getPreco());
        entity.setDisponivelSomenteNoLocal(itemCardapio.isDisponivelSomenteNoLocal());
        entity.setCaminhoFoto(itemCardapio.getCaminhoFoto());
        if (itemCardapio.getRestauranteId() != null) {
            entity.setRestauranteId(itemCardapio.getRestauranteId().toString());
        }
        return entity;
    }

    public ItemCardapio toDomain(ItemCardapioEntity entity) {
        return new ItemCardapio(
                entity.getId() != null ? UUID.fromString(entity.getId()) : null,
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.isDisponivelSomenteNoLocal(),
                entity.getCaminhoFoto(),
                entity.getRestauranteId() != null ? UUID.fromString(entity.getRestauranteId()) : null
        );
    }
}