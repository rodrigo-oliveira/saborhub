package com.saborhub.application.usecases;

import com.saborhub.infra.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarRestaurante {
    private final RestauranteRepository repository;

    public DeletarRestaurante(RestauranteRepository repository) {
        this.repository = repository;
    }

    public void deletar(String id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Restaurante não existe: " + id);
        }
        repository.deleteById(id);
    }
}
