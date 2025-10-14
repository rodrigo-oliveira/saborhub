package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RestauranteRepositoryInterface;

public class DeletarRestaurante {
    private final RestauranteRepositoryInterface repositorioRestaurante;

    public DeletarRestaurante(RestauranteRepositoryInterface repositorioRestaurante) {
        this.repositorioRestaurante = repositorioRestaurante;
    }

    public void deletar(String id) {
        this.repositorioRestaurante.deletar(id);
    }
}
