package com.saborhub.application.dto;

import com.saborhub.domain.entities.Endereco;
import com.saborhub.domain.enums.Perfil;

import java.time.ZonedDateTime;

public record UsuarioDto(
        String id,
        String nome,
        String email,
        String login,
        ZonedDateTime dataUltimaAlteracao,
        Perfil perfil,
        Endereco endereco
) {
}