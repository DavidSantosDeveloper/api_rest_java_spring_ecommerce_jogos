package com.jogos.ecommerce.domain.dto.output;

import com.jogos.ecommerce.domain.model.Produto;
import com.jogos.ecommerce.domain.model.Venda;

public record OUTPUT_ItemVenda_DTO(
Long id,    
Long quantidade,
double preco_unitario,
Venda venda,
Produto produto

) {
    
}
