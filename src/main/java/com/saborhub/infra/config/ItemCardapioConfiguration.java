package com.saborhub.infra.config;

import com.saborhub.application.gateways.ItemCardapioRepositoryInterface;
import com.saborhub.application.usecases.AtualizarItemCardapioUseCase;
import com.saborhub.application.usecases.DeletarItemCardapioUseCase;
import com.saborhub.application.usecases.ListarItensCardapioUseCase;
import com.saborhub.application.usecases.ObterItemCardapioUseCase;
import com.saborhub.application.usecases.RegistrarItemCardapioUseCase;
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
    RegistrarItemCardapioUseCase registrarItemCardapio(ItemCardapioRepositoryInterface repository) {
        return new RegistrarItemCardapioUseCase(repository);
    }

    @Bean
    AtualizarItemCardapioUseCase atualizarItemCardapio(ItemCardapioRepositoryInterface repository) {
        return new AtualizarItemCardapioUseCase(repository);
    }

    @Bean
    DeletarItemCardapioUseCase deletarItemCardapio(ItemCardapioRepositoryInterface repository) {
        return new DeletarItemCardapioUseCase(repository);
    }

    @Bean
    ListarItensCardapioUseCase listarItensCardapio(ItemCardapioRepositoryInterface repository) {
        return new ListarItensCardapioUseCase(repository);
    }

    @Bean
    ObterItemCardapioUseCase obterItemCardapio(ItemCardapioRepositoryInterface repository) {
        return new ObterItemCardapioUseCase(repository);
    }

    @Bean
    ItemCardapioEntityMapper itemCardapioEntityMapper() {
        return new ItemCardapioEntityMapper();
    }
}