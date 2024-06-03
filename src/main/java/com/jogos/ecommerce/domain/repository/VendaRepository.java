package com.jogos.ecommerce.domain.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jogos.ecommerce.domain.dto.output.OUTPUT_VendaDTO;
import com.jogos.ecommerce.domain.model.*;
@Repository
public interface VendaRepository extends JpaRepository<Venda,Long> {
    Venda findById(long id);

     @Query("SELECT new com.jogos.ecommerce.domain.dto.output.OUTPUT_VendaDTO(v.id,v.dt_venda,v.valor_total,v.usuario) FROM Venda v")
    List<OUTPUT_VendaDTO> findAllVendas();
    
}

