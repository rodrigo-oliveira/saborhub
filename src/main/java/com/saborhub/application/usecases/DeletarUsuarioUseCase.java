package com.saborhub.application.usecases;

import com.saborhub.application.gateways.UsuarioRepositoryInterface;

public class DeletarUsuario {
    private final UsuarioRepositoryInterface repositorioUsuario;

    public DeletarUsuario(UsuarioRepositoryInterface repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void deletar(String id) {
        this.repositorioUsuario.deletar(id);
    }
}
