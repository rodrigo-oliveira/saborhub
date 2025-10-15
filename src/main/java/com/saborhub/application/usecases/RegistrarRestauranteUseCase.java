package com.saborhub.application.usecases;

import com.saborhub.application.dto.RegistroRestauranteDto;
import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.domain.entities.Restaurante;

public class RegistrarRestauranteUseCase {
    private final RestauranteRepositoryInterface repository;

    public RegistrarRestauranteUseCase(RestauranteRepositoryInterface repository) {
        this.repository = repository;
    }

    public Restaurante executar(RegistroRestauranteDto dto, String donoId) {
        Restaurante restaurante = new Restaurante(
                dto.cnpj(),
                dto.nome(),
                dto.endereco(),
                dto.tipoCozinha(),
                dto.horarioFuncionamento(),
                donoId
        );
        return repository.save(restaurante);
    }
}
