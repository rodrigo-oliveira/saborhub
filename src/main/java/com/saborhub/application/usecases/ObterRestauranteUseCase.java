package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.domain.entities.Restaurante;

public class ObterRestauranteUseCase {

    private final RestauranteRepositoryInterface repository;

    public ObterRestauranteUseCase(RestauranteRepositoryInterface repository) {
        this.repository = repository;
    }

    public Restaurante obterPeloCnpj(String cnpj) {
        return this.repository.obterPeloCnpj(cnpj);
    }
    
    public Restaurante obterPorId(String id) {
        return this.repository.obterPorId(id);
    }
}
