package com.jogos.ecommerce.domain.dto.output;

import java.sql.Date;

public record OUTPUT_ClienteDTO(
    Long id,
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
