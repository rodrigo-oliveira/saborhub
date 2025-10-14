package com.saborhub.application.usecases;

import com.saborhub.application.dto.RegistroRestauranteDto;
import com.saborhub.infra.persistence.RestauranteEntity;
import com.saborhub.infra.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrarRestaurante {
    private final RestauranteRepository repository;

    public RegistrarRestaurante(RestauranteRepository repository) {
        this.repository = repository;
    }

    public RestauranteEntity executar(RegistroRestauranteDto dto, String donoId) {
        RestauranteEntity e = new RestauranteEntity();
        e.setCnpj(dto.cnpj());
        e.setNome(dto.nome());
        e.setEndereco(dto.endereco());
        e.setTipoCozinha(dto.tipoCozinha());
        e.setHorarioFuncionamento(dto.horarioFuncionamento());
        e.setDonoId(donoId);
        return repository.save(e);
    }
}
