package com.saborhub.application.usecases;

import com.saborhub.application.gateways.UsuarioRepositoryInterface;
import com.saborhub.domain.entities.Usuario;

import java.util.List;

public class ListarUsuariosUseCase {

    private final UsuarioRepositoryInterface repositorio;

    public ListarUsuariosUseCase(UsuarioRepositoryInterface repositorio) {
        this.repositorio = repositorio;
    }

    public List<Usuario> obterTodosUsuarios() {
        return this.repositorio.listarTodos();
    }
}
