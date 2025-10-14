package com.saborhub.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Restaurante {
    private String cnpj;
    private String nome;
    private Endereco endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private String donoId;

    public Restaurante(String cnpj, String nome, Endereco endereco, String tipoCozinha, String horarioFuncionamento, String donoId) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }
}