package com.jogos.ecommerce.domain.dto.output;

import com.jogos.ecommerce.domain.model.Cliente;
import com.jogos.ecommerce.domain.model.Funcionario;

public record OUTPUT_UsuarioDTO(
Long id,    
String email,
String senha,
String foto_perfil_url,
Cliente cliente,
Funcionario funcionario

) {
    
}
