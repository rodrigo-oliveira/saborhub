package com.saborhub.application.usecases;

import com.saborhub.application.gateways.ItemCardapioRepositoryInterface;
import com.saborhub.domain.entities.ItemCardapio;

import java.util.UUID;

public class ObterItemCardapioUseCase {

    private final ItemCardapioRepositoryInterface repository;

    public ObterItemCardapioUseCase(ItemCardapioRepositoryInterface repository) {
        this.repository = repository;
    }

    public ItemCardapio obterPorId(UUID id) {
        return this.repository.obterPorId(id);
    }
}