package com.saborhub.application.gateways;

import java.util.List;
import com.saborhub.domain.entities.usuario.Usuario;

public interface RepositorioUsuario {
    Usuario cadastrarUsuario(Usuario usuario);

    List<Usuario> listarTodos();

    Usuario obterUsuario(Long id);
}