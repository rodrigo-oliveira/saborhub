package com.saborhub.application.usecases;

import com.saborhub.application.dto.AtualizarRestauranteDto;
import com.saborhub.infra.persistence.RestauranteEntity;
import com.saborhub.infra.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarRestaurante {
    private final RestauranteRepository repository;

    public AtualizarRestaurante(RestauranteRepository repository) {
        this.repository = repository;
    }

    public RestauranteEntity atualizar(String id, AtualizarRestauranteDto dto) {
        RestauranteEntity e = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante not found: " + id));

        if (dto.nome() != null && !dto.nome().isBlank()) {
            e.setNome(dto.nome());
        }
        if (dto.endereco() != null) {
            e.setEndereco(dto.endereco());
        }
        if (dto.tipoCozinha() != null) {
            e.setTipoCozinha(dto.tipoCozinha());
        }
        if (dto.horarioFuncionamento() != null) {
            e.setHorarioFuncionamento(dto.horarioFuncionamento());
        }

        return repository.save(e);
    }
}
