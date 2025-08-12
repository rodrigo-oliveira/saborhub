package com.saborhub.domain.entities.usuario;

public record AtualizarUsuarioDto(
        String nome,
        String email,
        String login,
        Endereco endereco
) {
}
