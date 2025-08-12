package com.saborhub.application.gateways;

import java.util.List;

import com.saborhub.domain.entities.usuario.Usuario;

public interface RepositorioUsuario {
    Usuario findByLogin(String login);

    void save(Usuario usuario);

    List<Usuario> listarTodos();

    Usuario obterUsuario(String id);
    
    void deletarPorId(String id);
}