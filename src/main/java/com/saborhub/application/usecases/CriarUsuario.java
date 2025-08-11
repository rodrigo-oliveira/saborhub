package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RepositorioUsuario;
import com.saborhub.domain.entities.usuario.Usuario;

public class CriarUsuario {
    private final RepositorioUsuario repositorio;

    public CriarUsuario(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        return repositorio.cadastrarUsuario(usuario);
    }
}