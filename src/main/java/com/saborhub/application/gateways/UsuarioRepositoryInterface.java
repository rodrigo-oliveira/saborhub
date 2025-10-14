package com.saborhub.application.gateways;

import com.saborhub.domain.entities.Usuario;

import java.util.List;

public interface UsuarioRepositoryInterface {
    Usuario findByLogin(String login);

    void save(Usuario usuario);

    List<Usuario> listarTodos();

    Usuario obterPeloId(String id);

    void deletar(String id);
}