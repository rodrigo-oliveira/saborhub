package com.saborhub.application.usecases;

import com.saborhub.application.dto.AtualizarRestauranteDto;
import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.domain.entities.Restaurante;
import com.saborhub.domain.exceptions.RecursoNaoEncontradoException;

public class AtualizarRestauranteUseCase {
    private final RestauranteRepositoryInterface repository;

    public AtualizarRestauranteUseCase(RestauranteRepositoryInterface repository) {
        this.repository = repository;
    }

    public Restaurante atualizar(String id, AtualizarRestauranteDto dto) {
        Restaurante restauranteExistente = repository.obterPorId(id);
        if (restauranteExistente == null) {
            throw new RecursoNaoEncontradoException("Restaurante não encontrado: " + id);
        }

        // Atualizar apenas os campos que foram fornecidos
        if (dto.nome() != null && !dto.nome().isBlank()) {
            restauranteExistente.setNome(dto.nome());
        }
        if (dto.endereco() != null) {
            restauranteExistente.setEndereco(dto.endereco());
        }
        if (dto.tipoCozinha() != null) {
            restauranteExistente.setTipoCozinha(dto.tipoCozinha());
        }
        if (dto.horarioFuncionamento() != null) {
            restauranteExistente.setHorarioFuncionamento(dto.horarioFuncionamento());
        }

        return repository.save(restauranteExistente);
    }
}
