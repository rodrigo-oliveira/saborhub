package com.saborhub;

import org.junit.jupiter.api.Test;

import com.saborhub.domain.entities.usuario.Endereco;
import com.saborhub.domain.entities.usuario.Usuario;
import com.saborhub.domain.entities.usuario.UsuarioRole;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testUsuarioConstructorAndFields() {
        ZonedDateTime now = ZonedDateTime.now();

        class UsuarioConcreto extends Usuario {
            public UsuarioConcreto(String id, String nome, String email, String login, String senha, java.time.ZonedDateTime dataUltimaAlteracao, UsuarioRole role, Endereco endereco) {
                super(id, nome, email, login, senha, dataUltimaAlteracao, role, endereco);
            }
        }
    Usuario usuario = new UsuarioConcreto("1", "Teste", "teste@email.com", "teste", "abc123", now, UsuarioRole.CLIENTE, new Endereco("Rua A", "123", "São Paulo", "SP", "18000000"));

        assertEquals("Teste", usuario.getNome());
        assertEquals("teste@email.com", usuario.getEmail());
        assertEquals("teste", usuario.getLogin());
        assertEquals("abc123", usuario.getSenha());
        assertEquals(now, usuario.getDataUltimaAlteracao());
    }
}
