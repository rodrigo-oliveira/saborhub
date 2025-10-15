package com.saborhub.infra.config;

import com.saborhub.application.gateways.RestauranteRepositoryInterface;
import com.saborhub.application.usecases.AtualizarRestauranteUseCase;
import com.saborhub.application.usecases.DeletarRestauranteUseCase;
import com.saborhub.application.usecases.ListarRestaurantesUseCase;
import com.saborhub.application.usecases.ObterRestauranteUseCase;
import com.saborhub.application.usecases.RegistrarRestauranteUseCase;
import com.saborhub.infra.gateways.RestauranteEntityMapper;
import com.saborhub.infra.gateways.RestauranteEntityRepository;
import com.saborhub.infra.repository.RestauranteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfiguration {
    
    @Bean
    RestauranteRepositoryInterface criarRestauranteRepositorioJpa(
            RestauranteRepository repository, 
            RestauranteEntityMapper mapper
    ) {
        return new RestauranteEntityRepository(repository, mapper);
    }

    @Bean
    RegistrarRestauranteUseCase registrarRestaurante(RestauranteRepositoryInterface repository) {
        return new RegistrarRestauranteUseCase(repository);
    }

    @Bean
    AtualizarRestauranteUseCase atualizarRestaurante(RestauranteRepositoryInterface repository) {
        return new AtualizarRestauranteUseCase(repository);
    }

    @Bean
    DeletarRestauranteUseCase deletarRestaurante(RestauranteRepositoryInterface repository) {
        return new DeletarRestauranteUseCase(repository);
    }

    @Bean
    ListarRestaurantesUseCase listarRestaurantes(RestauranteRepositoryInterface repository) {
        return new ListarRestaurantesUseCase(repository);
    }

    @Bean
    ObterRestauranteUseCase obterRestaurante(RestauranteRepositoryInterface repository) {
        return new ObterRestauranteUseCase(repository);
    }

    @Bean
    RestauranteEntityMapper restauranteEntityMapper() {
        return new RestauranteEntityMapper();
    }
}