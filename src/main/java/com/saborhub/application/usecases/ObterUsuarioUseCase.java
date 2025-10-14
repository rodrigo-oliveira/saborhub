package com.saborhub.application.usecases;

import com.saborhub.application.gateways.UsuarioRepositoryInterface;
import com.saborhub.domain.entities.Usuario;

public class ObterUsuario {

    private final UsuarioRepositoryInterface repositorio;

    public ObterUsuario(UsuarioRepositoryInterface repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario obterPeloId(String id) {
        return this.repositorio.obterPeloId(id);
    }
}