package com.jogos.ecommerce.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jogos.ecommerce.domain.dto.output.OUTPUT_FuncionarioDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ItemCarrinhoDTO;
import com.jogos.ecommerce.domain.model.*;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho,Long> {
    ItemCarrinho findById(long id);
    
    @Query("SELECT new com.jogos.ecommerce.domain.dto.output.OUTPUT_ItemCarrinhoDTO(i.id,i.quantidade,i.preco_unitario,i.carrinho,i.produto) FROM Funcionario i")
    List<OUTPUT_ItemCarrinhoDTO> findAllItemCarrinho();

}

