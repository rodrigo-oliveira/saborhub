package com.saborhub.infra.config;

import com.saborhub.application.gateways.ItemCardapioRepositoryInterface;
import com.saborhub.application.usecases.ListarItensCardapio;
import com.saborhub.application.usecases.RegistrarItemCardapio;
import com.saborhub.infra.gateways.ItemCardapioEntityMapper;
import com.saborhub.infra.gateways.ItemCardapioEntityRepository;
import com.saborhub.infra.repository.ItemCardapioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCardapioConfiguration {
    
    @Bean
    ItemCardapioRepositoryInterface criarItemCardapioRepositorioJpa(
            ItemCardapioRepository repository, 
            ItemCardapioEntityMapper mapper
    ) {
        return new ItemCardapioEntityRepository(repository, mapper);
    }

    @Bean
    RegistrarItemCardapio registrarItemCardapio(ItemCardapioRepositoryInterface repository) {
        return new RegistrarItemCardapio(repository);
    }

    @Bean
    ListarItensCardapio listarItensCardapio(ItemCardapioRepositoryInterface repository) {
        return new ListarItensCardapio(repository);
    }

    @Bean
    ItemCardapioEntityMapper itemCardapioEntityMapper() {
        return new ItemCardapioEntityMapper();
    }
}