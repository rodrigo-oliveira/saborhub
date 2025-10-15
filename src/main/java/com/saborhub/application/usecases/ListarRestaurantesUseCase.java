package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.domain.entities.Restaurante;

import java.util.List;

public class ListarRestaurantesUseCase {
    private final RestauranteRepositoryInterface repository;

    public ListarRestaurantesUseCase(RestauranteRepositoryInterface repository) {
        this.repository = repository;
    }

    public List<Restaurante> obterTodosRestaurantes() {
        return repository.listarTodos();
    }
}
