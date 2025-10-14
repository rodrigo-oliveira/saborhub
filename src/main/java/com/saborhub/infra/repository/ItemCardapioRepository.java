package com.saborhub.infra.repository;

import com.saborhub.infra.persistence.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, String> {
    List<ItemCardapioEntity> findByRestauranteId(String restauranteId);
}