package com.saborhub.application.gateways;

import com.saborhub.domain.entities.Restaurante;

import java.util.List;

public interface RestauranteRepositoryInterface {
    Restaurante obterPeloCnpj(String cnpj);

    void save(Restaurante restaurante);

    List<Restaurante> listarTodos();

    void deletar(String id);
}