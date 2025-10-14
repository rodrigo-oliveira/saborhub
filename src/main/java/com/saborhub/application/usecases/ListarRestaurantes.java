package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.domain.entities.Restaurante;

import java.util.List;

public class ListarRestaurantes {

    private final RestauranteRepositoryInterface repositorio;

    public ListarRestaurantes(RestauranteRepositoryInterface repositorio) {
        this.repositorio = repositorio;
    }

    public List<Restaurante> obterTodosRestaurantes() {
        return this.repositorio.listarTodos();
    }
}