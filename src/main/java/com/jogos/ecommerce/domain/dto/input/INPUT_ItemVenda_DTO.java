package com.jogos.ecommerce.domain.dto.input;

import com.jogos.ecommerce.domain.model.Produto;
import com.jogos.ecommerce.domain.model.Venda;

public record INPUT_ItemVenda_DTO(
Long quantidade,
double preco_unitario,
Venda venda,
Produto produto

) {
    
}
