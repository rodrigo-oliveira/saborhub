package com.saborhub.application.usecases;

import com.saborhub.application.dto.RegistroItemCardapioDto;
import com.saborhub.application.gateways.ItemCardapioRepositoryInterface;
import com.saborhub.domain.entities.ItemCardapio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrarItemCardapio {
    private final ItemCardapioRepositoryInterface repository;

    public RegistrarItemCardapio(ItemCardapioRepositoryInterface repository) {
        this.repository = repository;
    }

    public ItemCardapio executar(RegistroItemCardapioDto dto, UUID restauranteId) {
        ItemCardapio itemCardapio = new ItemCardapio(
                null,
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.disponivelSomenteNoLocal(),
                dto.caminhoFoto(),
                restauranteId
        );
        
        return repository.save(itemCardapio);
    }
}