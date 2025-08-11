package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RepositorioUsuario;
import com.saborhub.domain.entities.usuario.Usuario;

public class ObterUsuario {

    private final RepositorioUsuario repositorio;

    public ObterUsuario(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario obterUsuarioPorId(Long id) {
        return this.repositorio.obterUsuario(id);
    }
}