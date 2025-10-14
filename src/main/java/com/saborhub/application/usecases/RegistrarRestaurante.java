package com.saborhub.application.usecases;

import com.saborhub.application.dto.RegistroRestauranteDto;
import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.domain.entities.Restaurante;

public class RegistrarRestaurante {
    private final RestauranteRepositoryInterface repositorio;

    public RegistrarRestaurante(RestauranteRepositoryInterface repositorio) {
        this.repositorio = repositorio;
    }

    public void executar(RegistroRestauranteDto dados) {
        if (repositorio.obterPeloCnpj(dados.cnpj()) != null) {
            throw new IllegalArgumentException("Restaurante com este CNPJ já existe.");
        }

        Restaurante novoRestaurante = new Restaurante(
                dados.cnpj(),
                dados.nome(),
                dados.endereco(),
                dados.tipoCozinha(),
                dados.horarioFuncionamento(),
                dados.donoId()
        );

        repositorio.save(novoRestaurante);
    }
}
