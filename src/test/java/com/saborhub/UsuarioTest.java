package com.saborhub;

import com.saborhub.domain.entities.Endereco;
import com.saborhub.domain.entities.Usuario;
import com.saborhub.domain.enums.Perfil;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioTest {

    @Test
    public void testUsuarioConstructorAndFields() {
        ZonedDateTime now = ZonedDateTime.now();

        class UsuarioConcreto extends Usuario {
            public UsuarioConcreto(String id, String nome, String email, String login, String senha, java.time.ZonedDateTime dataUltimaAlteracao, Perfil perfil, Endereco endereco) {
                super(id, nome, email, login, senha, dataUltimaAlteracao, perfil, endereco);
            }
        }
        Usuario usuario = new UsuarioConcreto("1", "Teste", "teste@email.com", "teste", "abc123", now, Perfil.ADMIN, new Endereco("1", "Teste", "teste@email.com", "teste", "abc123", "SP", "18000000"));

        assertEquals("Teste", usuario.getNome());
        assertEquals("teste@email.com", usuario.getEmail());
        assertEquals("teste", usuario.getLogin());
        assertEquals("abc123", usuario.getSenha());
        assertEquals(now, usuario.getDataUltimaAlteracao());
    }
}
