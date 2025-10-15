package com.saborhub.application.usecases;

import com.saborhub.application.dto.AtualizarItemCardapioDto;
import com.saborhub.application.gateways.ItemCardapioRepositoryInterface;
import com.saborhub.domain.entities.ItemCardapio;
import com.saborhub.domain.exceptions.RecursoNaoEncontradoException;

import java.util.UUID;

public class AtualizarItemCardapioUseCase {
    private final ItemCardapioRepositoryInterface repository;

    public AtualizarItemCardapioUseCase(ItemCardapioRepositoryInterface repository) {
        this.repository = repository;
    }

    public ItemCardapio atualizar(UUID id, AtualizarItemCardapioDto dto) {
        ItemCardapio itemExistente = repository.obterPorId(id);
        if (itemExistente == null) {
            throw new RecursoNaoEncontradoException("Item de cardápio não encontrado: " + id);
        }

        // Criar novo objeto com os dados atualizados
        ItemCardapio itemAtualizado = new ItemCardapio(
                itemExistente.getId(),
                dto.nome() != null && !dto.nome().isBlank() ? dto.nome() : itemExistente.getNome(),
                dto.descricao() != null ? dto.descricao() : itemExistente.getDescricao(),
                dto.preco() != null ? dto.preco() : itemExistente.getPreco(),
                dto.disponivelSomenteNoLocal(),
                dto.caminhoFoto() != null ? dto.caminhoFoto() : itemExistente.getCaminhoFoto(),
                itemExistente.getRestauranteId()
        );

        return repository.save(itemAtualizado);
    }
}