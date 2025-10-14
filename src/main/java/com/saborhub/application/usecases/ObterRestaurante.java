package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.domain.entities.Restaurante;

public class ObterRestaurante {

    private final RestauranteRepositoryInterface repository;

    public ObterRestaurante(RestauranteRepositoryInterface repository) {
        this.repository = repository;
    }

    public Restaurante obterPeloCnpj(String cnpj) {
        return this.repository.obterPeloCnpj(cnpj);
    }
}
