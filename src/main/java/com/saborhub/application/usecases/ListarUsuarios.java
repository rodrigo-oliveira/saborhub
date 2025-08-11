package com.saborhub.application.usecases;

import java.util.List;
import com.saborhub.application.gateways.RepositorioUsuario;
import com.saborhub.domain.entities.usuario.Usuario;

public class ListarUsuarios {

    private final RepositorioUsuario repositorio;

    public ListarUsuarios(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public List<Usuario> obterTodosUsuarios(){
        return this.repositorio.listarTodos();
    }
}