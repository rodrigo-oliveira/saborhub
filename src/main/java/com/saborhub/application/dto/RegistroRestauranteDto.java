package com.saborhub.application.dto;

import com.saborhub.domain.entities.Endereco;

public record RegistroRestauranteDto(
        String cnpj,
        String nome,
        Endereco endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        String donoId
) {

}