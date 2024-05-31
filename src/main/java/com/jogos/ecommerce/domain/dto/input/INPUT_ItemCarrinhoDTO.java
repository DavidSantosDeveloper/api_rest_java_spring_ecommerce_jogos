package com.jogos.ecommerce.domain.dto.input;

import com.jogos.ecommerce.domain.model.Carrinho;
import com.jogos.ecommerce.domain.model.Produto;

public record INPUT_ItemCarrinhoDTO(
    Long quantidade,
    double preco_unitario,
    Carrinho carrinho,
    Produto produto
    
) {
    
}
