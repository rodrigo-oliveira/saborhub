package com.saborhub.infra.persistence;

import com.saborhub.domain.entities.Endereco;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "restaurantes")
public class RestauranteEntity {
    @Id
    private String id;

    private String cnpj;
    private String nome;

    @Embedded
    private Endereco endereco;

    private String tipoCozinha;
    private String horarioFuncionamento;
    
    @Column(name = "dono_id")
    private String donoId;

    public RestauranteEntity() {
    }

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public String getDonoId() {
        return donoId;
    }

    public void setDonoId(String donoId) {
        this.donoId = donoId;
    }
}
