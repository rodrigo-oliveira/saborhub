package com.saborhub.infrastructure.controller;

import java.time.ZonedDateTime;

public record UsuarioDto(
        Long id,
        String nome,
        String email,
        String senha,
        String login,
        ZonedDateTime dataUltimaAlteracao
) {
}