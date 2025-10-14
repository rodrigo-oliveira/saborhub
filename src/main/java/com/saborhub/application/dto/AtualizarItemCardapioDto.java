package com.saborhub.application.dto;

import java.math.BigDecimal;

public record AtualizarItemCardapioDto(
        String nome,
        String descricao,
        BigDecimal preco,
        boolean disponivelSomenteNoLocal,
        String caminhoFoto
) {
}