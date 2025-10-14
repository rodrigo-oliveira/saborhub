package com.saborhub.application.usecases;

import com.saborhub.application.gateways.ItemCardapioRepositoryInterface;
import com.saborhub.domain.entities.ItemCardapio;
import com.saborhub.domain.exceptions.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletarItemCardapio {
    private final ItemCardapioRepositoryInterface repository;

    public DeletarItemCardapio(ItemCardapioRepositoryInterface repository) {
        this.repository = repository;
    }

    public void deletar(UUID id) {
        ItemCardapio item = repository.obterPorId(id);
        if (item == null) {
            throw new RecursoNaoEncontradoException("Item de cardápio não encontrado: " + id);
        }
        repository.deletar(id);
    }
}