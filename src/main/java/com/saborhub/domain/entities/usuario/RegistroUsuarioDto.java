package com.saborhub.domain.entities.usuario;

public record RegistroUsuarioDto(
        String id,
        String nome,
        String email,
        String login,
        String senha,
        UsuarioRole role,
        Endereco endereco
) {
}