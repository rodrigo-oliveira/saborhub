package com.saborhub.infra.gateways;

import com.saborhub.application.gateways.ItemCardapioRepositoryInterface;
import com.saborhub.domain.entities.ItemCardapio;
import com.saborhub.infra.persistence.ItemCardapioEntity;
import com.saborhub.infra.repository.ItemCardapioRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemCardapioEntityRepository implements ItemCardapioRepositoryInterface {
    private final ItemCardapioRepository repositorio;
    private final ItemCardapioEntityMapper mapper;

    public ItemCardapioEntityRepository(ItemCardapioRepository repositorio, ItemCardapioEntityMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public ItemCardapio save(ItemCardapio itemCardapio) {
        ItemCardapioEntity entity = mapper.toEntity(itemCardapio);
        ItemCardapioEntity savedEntity = repositorio.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<ItemCardapio> listarPorRestaurante(UUID restauranteId) {
        return repositorio.findByRestauranteId(restauranteId.toString())
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public ItemCardapio obterPorId(UUID id) {
        return repositorio.findById(id.toString())
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public void deletar(UUID id) {
        repositorio.deleteById(id.toString());
    }
}