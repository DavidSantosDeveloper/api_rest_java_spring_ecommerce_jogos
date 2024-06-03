package com.jogos.ecommerce.domain.dto.input;

import java.sql.Date;

public record INPUT_ClienteDTO(
    String nome,
    String telefone,
    Date dt_nasc,
    String cpf,
    String cep,
    String pais,
    String estado,
    String cidade,
    String  bairro,
    String logradouro,
    String numero,
    String ponto_de_referencia
    ) {
    
}
