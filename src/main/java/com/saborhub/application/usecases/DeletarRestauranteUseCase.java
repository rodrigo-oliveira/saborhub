package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.domain.exceptions.RecursoNaoEncontradoException;

public class DeletarRestauranteUseCase {
    private final RestauranteRepositoryInterface repository;

    public DeletarRestauranteUseCase(RestauranteRepositoryInterface repository) {
        this.repository = repository;
    }

    public void deletar(String id) {
        if (repository.obterPorId(id) == null) {
            throw new RecursoNaoEncontradoException("Restaurante não encontrado: " + id);
        }
        repository.deletar(id);
    }
}
