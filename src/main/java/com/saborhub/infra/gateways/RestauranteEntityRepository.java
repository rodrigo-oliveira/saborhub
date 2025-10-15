package com.saborhub.infra.gateways;

import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.domain.entities.Restaurante;
import com.saborhub.infra.repository.RestauranteRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RestauranteEntityRepository implements RestauranteRepositoryInterface {
    private final RestauranteRepository repository;
    private final RestauranteEntityMapper mapper;

    public RestauranteEntityRepository(RestauranteRepository repository, RestauranteEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Restaurante obterPeloCnpj(String cnpj) {
        return repository.findByCnpj(cnpj)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public Restaurante obterPorId(String id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public Restaurante save(Restaurante restaurante) {
        var entity = mapper.toEntity(restaurante);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<Restaurante> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(String id) {
        repository.deleteById(id);
    }
}