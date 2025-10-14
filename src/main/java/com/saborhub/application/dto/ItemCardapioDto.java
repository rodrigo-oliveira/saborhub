package com.saborhub.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemCardapioDto(
        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean disponivelSomenteNoLocal,
        String caminhoFoto,
        UUID restauranteId
) {
}