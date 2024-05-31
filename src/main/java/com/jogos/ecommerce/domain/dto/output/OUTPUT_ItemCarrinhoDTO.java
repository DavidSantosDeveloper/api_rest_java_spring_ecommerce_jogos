package com.jogos.ecommerce.domain.dto.output;

import com.jogos.ecommerce.domain.model.Carrinho;
import com.jogos.ecommerce.domain.model.Produto;

public record OUTPUT_ItemCarrinhoDTO(
    Long id,
    Long quantidade,
    double preco_unitario,
    Carrinho carrinho,
    Produto produto
    
) {
    
}
