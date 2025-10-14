package com.saborhub.application.usecases;

import com.saborhub.domain.entities.Restaurante;
import com.saborhub.infra.persistence.RestauranteEntity;
import com.saborhub.infra.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarRestaurantes {
    private final RestauranteRepository repository;

    public ListarRestaurantes(RestauranteRepository repository) {
        this.repository = repository;
    }

    public List<Restaurante> obterTodosRestaurantes() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private Restaurante toDomain(RestauranteEntity e) {
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
