package com.jogos.ecommerce.domain.dto.output;

import java.sql.Date;

import com.jogos.ecommerce.domain.model.Cargo;

public record OUTPUT_FuncionarioDTO
(
 Long id,
  String nome,
  String telefone,
 Date dt_nasc,
 String cpf,
 double salario,
 String cep,
 String pais, 
 String estado,
String cidade,
 String  bairro,
String logradouro,
String numero,
String ponto_de_referencia,
Cargo cargo
)
{

}
