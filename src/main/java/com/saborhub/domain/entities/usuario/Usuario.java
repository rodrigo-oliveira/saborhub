package com.saborhub.domain.entities.usuario;

import java.time.ZonedDateTime;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String login;
    private String password;
    private ZonedDateTime dataUltimaAlteracao;
    private UsuarioRole role;
    private Endereco endereco;

    public Usuario(
        String id,
        String nome,
        String email,
        String login,
        String password,
        ZonedDateTime dataUltimaAlteracao,
        UsuarioRole role,
        Endereco endereco
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.password = password;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.role = role;
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZonedDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(ZonedDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
