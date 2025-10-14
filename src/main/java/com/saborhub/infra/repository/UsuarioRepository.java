package com.saborhub.infra.repository;

import com.saborhub.infra.persistence.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    UserDetails findByLogin(String login);
    UsuarioEntity findUsuarioEntityByLogin(String login);
}