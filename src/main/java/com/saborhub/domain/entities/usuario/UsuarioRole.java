package com.saborhub.domain.entities.usuario;

public enum UsuarioRole {
    ADMIN("admin"),
    CLIENTE("cliente"),
    RESTAURANTE("restaurante");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}