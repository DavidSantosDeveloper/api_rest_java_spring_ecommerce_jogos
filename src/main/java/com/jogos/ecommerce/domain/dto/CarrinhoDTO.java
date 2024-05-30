package com.jogos.ecommerce.domain.dto;

import com.jogos.ecommerce.domain.model.Usuario;

public record CarrinhoDTO(Long id,Usuario usuario,double total) {
    
}
