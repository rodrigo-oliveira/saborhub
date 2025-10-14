package com.saborhub.infra.persistence;

import com.saborhub.domain.entities.Endereco;
import com.saborhub.domain.enums.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private ZonedDateTime dataUltimaAlteracao;
    private Perfil perfil;

    @org.hibernate.annotations.Type(com.vladmihalcea.hibernate.type.json.JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Endereco endereco;

    public UsuarioEntity(
            String nome,
            String email,
            String login,
            String senha,
            ZonedDateTime dataUltimaAlteracao,
            Perfil perfil,
            Endereco endereco
    ) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.perfil = perfil;
        this.endereco = endereco;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.perfil) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE"),
                    new SimpleGrantedAuthority("ROLE_DONO_RESTAURANTE"));
            case CLIENTE -> List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
            case DONO_RESTAURANTE -> List.of(new SimpleGrantedAuthority("ROLE_DONO_RESTAURANTE"));
        };
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void touchUltimaAlteracao() {
        this.dataUltimaAlteracao = ZonedDateTime.now();
    }
}