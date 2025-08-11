package com.saborhub.domain.entities.usuario;

import java.time.ZonedDateTime;

public record UsuarioDto(
        String id,
        String nome,
        String email,
        String login,
        ZonedDateTime dataUltimaAlteracao,
        UsuarioRole role,
        Endereco endereco
) {
}