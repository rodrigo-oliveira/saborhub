package com.saborhub.domain.entities;

import com.saborhub.domain.enums.Perfil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private ZonedDateTime dataUltimaAlteracao;
    private Perfil perfil;
    private Endereco endereco;

    public Usuario(
            String id,
            String nome,
            String email,
            String login,
            String senha,
            ZonedDateTime dataUltimaAlteracao,
            Perfil perfil,
            Endereco endereco
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.perfil = perfil;
        this.endereco = endereco;
    }
}