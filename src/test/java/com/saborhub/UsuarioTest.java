package com.saborhub;

import org.junit.jupiter.api.Test;

import com.saborhub.domain.entities.usuario.Usuario;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testUsuarioConstructorAndFields() {
        ZonedDateTime now = ZonedDateTime.now();

        class UsuarioConcreto extends Usuario {
            public UsuarioConcreto(Long id, String nome, String email, String login, String senha, java.time.ZonedDateTime dataUltimaAlteracao) {
                super(id, nome, email, login, senha, dataUltimaAlteracao);
            }
        }
        Usuario usuario = new UsuarioConcreto(1L, "João", "joao@email.com", "joaologin", "senha123", now);

        assertEquals("João", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals("joaologin", usuario.getLogin());
        assertEquals("senha123", usuario.getSenha());
        assertEquals(now, usuario.getDataUltimaAlteracao());
    }
}
