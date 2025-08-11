package com.saborhub.application.gateways;

import java.util.List;
import com.saborhub.domain.entities.usuario.Usuario;

public interface RepositorioUsuario {
    List<Usuario> listarTodos();

    Usuario obterUsuario(String id);
}