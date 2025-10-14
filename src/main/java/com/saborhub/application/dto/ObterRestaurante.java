package com.saborhub.application.dto;

import com.saborhub.domain.entities.Restaurante;
import com.saborhub.infra.persistence.RestauranteEntity;
import com.saborhub.infra.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class ObterRestaurante {
    private final RestauranteRepository repository;

    public ObterRestaurante(RestauranteRepository repository) {
        this.repository = repository;
    }

    public Restaurante obterPeloId(String id) {
        RestauranteEntity e = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante not found: " + id));
        return new Restaurante(
                e.getCnpj(),
                e.getNome(),
                e.getEndereco(),
                e.getTipoCozinha(),
                e.getHorarioFuncionamento(),
                e.getDonoId()
        );
    }
}
