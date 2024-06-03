package com.jogos.ecommerce.domain.dto.output;

import java.sql.Date;
import com.jogos.ecommerce.domain.model.Usuario;

public record OUTPUT_VendaDTO (
Long id,
Date dt_venda,
double valor_total,
Usuario usuario

){}
