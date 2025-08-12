package com.saborhub.domain.entities.usuario;

public record AlterarSenhaDto(
    String senhaAtual,
    String novaSenha
) {
}
