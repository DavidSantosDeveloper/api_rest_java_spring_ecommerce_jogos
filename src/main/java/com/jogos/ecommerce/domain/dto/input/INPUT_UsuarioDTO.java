package com.jogos.ecommerce.domain.dto.input;

import com.jogos.ecommerce.domain.model.Cliente;
import com.jogos.ecommerce.domain.model.Funcionario;

public record INPUT_UsuarioDTO(
String email,
String senha,
String foto_perfil_url,
Cliente cliente,
Funcionario funcionario

) {
    
}
