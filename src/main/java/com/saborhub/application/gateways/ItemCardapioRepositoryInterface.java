package com.saborhub.application.gateways;

import com.saborhub.domain.entities.ItemCardapio;

import java.util.List;
import java.util.UUID;

public interface ItemCardapioRepositoryInterface {
    ItemCardapio save(ItemCardapio itemCardapio);
    
    List<ItemCardapio> listarPorRestaurante(UUID restauranteId);
    
    ItemCardapio obterPorId(UUID id);
    
    void deletar(UUID id);
}