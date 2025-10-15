package com.saborhub.application.gateways;

import com.saborhub.domain.entities.Restaurante;

import java.util.List;

public interface RestauranteRepositoryInterface {
    Restaurante obterPeloCnpj(String cnpj);
    
    Restaurante obterPorId(String id);

    Restaurante save(Restaurante restaurante);

    List<Restaurante> listarTodos();

    void deletar(String id);
}