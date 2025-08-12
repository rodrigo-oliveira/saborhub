package com.saborhub.application.usecases;

import com.saborhub.application.gateways.RepositorioUsuario;

public class DeletarUsuario {
    private final RepositorioUsuario repositorioUsuario;

    public DeletarUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void deletar(String id) {
        this.repositorioUsuario.deletarPorId(id);
    }
}
