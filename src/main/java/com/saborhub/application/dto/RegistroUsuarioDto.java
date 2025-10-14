package com.saborhub.application.dto;

import com.saborhub.domain.entities.Endereco;
import com.saborhub.domain.enums.Perfil;

public record RegistroUsuarioDto(
        String id,
        String nome,
        String email,
        String login,
        String senha,
        Perfil perfil,
        Endereco endereco
) {
}