package com.jogos.ecommerce.domain.dto.output;

import com.jogos.ecommerce.domain.model.Usuario;

public record OUTPUT_CarrinhoDTO(Long id,Usuario usuario,double total) {
    
}
