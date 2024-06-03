package com.jogos.ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jogos.ecommerce.domain.dto.output.OUTPUT_ItemVenda_DTO;
import com.jogos.ecommerce.domain.model.ItemVenda;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda,Long> {
    ItemVenda findById(long id);

    @Query("SELECT new com.jogos.ecommerce.domain.dto.output.OUTPUT_ItemVenda_DTO(i.id, i.quantidade,i.preco_unitario,i.venda,i.produto) FROM ItemVenda i")
    List<OUTPUT_ItemVenda_DTO> findAllItemVenda();
    
}

