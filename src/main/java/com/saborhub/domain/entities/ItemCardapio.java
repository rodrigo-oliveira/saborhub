package com.saborhub.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ItemCardapio implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean disponivelSomenteNoLocal;
    private String caminhoFoto;
    private UUID restauranteId;

    public ItemCardapio(UUID id, String nome, String descricao, BigDecimal preco, boolean disponivelSomenteNoLocal, String caminhoFoto, UUID restauranteId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivelSomenteNoLocal = disponivelSomenteNoLocal;
        this.caminhoFoto = caminhoFoto;
        this.restauranteId = restauranteId;
    }
}