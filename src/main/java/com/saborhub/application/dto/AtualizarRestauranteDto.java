package com.saborhub.application.dto;

import com.saborhub.domain.entities.Endereco;

public record AtualizarRestauranteDto(
        String nome,
        Endereco endereco,
        String tipoCozinha,
        String horarioFuncionamento
) {
}
