package com.saborhub.application.usecases;

import com.saborhub.application.gateways.ItemCardapioRepositoryInterface;
import com.saborhub.domain.entities.ItemCardapio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarItensCardapio {
    private final ItemCardapioRepositoryInterface repository;

    public ListarItensCardapio(ItemCardapioRepositoryInterface repository) {
        this.repository = repository;
    }

    public List<ItemCardapio> listarPorRestaurante(UUID restauranteId) {
        return repository.listarPorRestaurante(restauranteId);
    }
}