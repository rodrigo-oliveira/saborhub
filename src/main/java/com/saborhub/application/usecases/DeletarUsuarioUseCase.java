package com.saborhub.application.usecases;

import com.saborhub.application.gateways.UsuarioRepositoryInterface;

public class DeletarUsuarioUseCase {
    private final UsuarioRepositoryInterface repositorioUsuario;

    public DeletarUsuarioUseCase(UsuarioRepositoryInterface repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void deletar(String id) {
        this.repositorioUsuario.deletar(id);
    }
}
