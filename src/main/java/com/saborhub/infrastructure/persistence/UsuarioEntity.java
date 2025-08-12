package com.saborhub.infrastructure.persistence;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.saborhub.domain.entities.usuario.Endereco;
import com.saborhub.domain.entities.usuario.UsuarioRole;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String password;
    private ZonedDateTime dataUltimaAlteracao;

    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    @org.hibernate.annotations.Type(com.vladmihalcea.hibernate.type.json.JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Endereco endereco;

    public UsuarioEntity(
        String nome,
        String email,
        String login,
        String password,
        ZonedDateTime dataUltimaAlteracao,
        UsuarioRole role,
        Endereco endereco
    ) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.password = password;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.role = role;
        this.endereco = endereco;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.role) {
            case ADMIN -> List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_CLIENTE"),
                new SimpleGrantedAuthority("ROLE_RESTAURANTE"));
            case CLIENTE -> List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
            case RESTAURANTE -> List.of(new SimpleGrantedAuthority("ROLE_RESTAURANTE"));
        };
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
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
}