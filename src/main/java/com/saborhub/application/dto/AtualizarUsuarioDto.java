package com.saborhub.application.dto;

import com.saborhub.domain.entities.Endereco;

public record AtualizarUsuarioDto(
        String nome,
        String email,
        String login,
        Endereco endereco
) {
}
