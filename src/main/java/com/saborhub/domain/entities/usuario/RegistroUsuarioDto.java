package com.saborhub.domain.entities.usuario;

public record RegistroUsuarioDto(
        String id,
        String nome,
        String email,
        String login,
        String password,
        UsuarioRole role,
        Endereco endereco
) {
}