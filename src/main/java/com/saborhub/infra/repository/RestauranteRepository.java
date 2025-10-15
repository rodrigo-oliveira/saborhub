package com.saborhub.infra.repository;

import com.saborhub.infra.persistence.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, String> {
    Optional<RestauranteEntity> findByCnpj(String cnpj);
}
