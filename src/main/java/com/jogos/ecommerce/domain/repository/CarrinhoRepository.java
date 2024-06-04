package com.jogos.ecommerce.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.dto.*;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_CarrinhoDTO;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho,Long> {
    Carrinho findById(long id);

    //os campos c.id,... se referem aos campos do objeto DTO, ja c é o apelido da tabela
    @Query("SELECT new com.jogos.ecommerce.domain.dto.output.OUTPUT_CarrinhoDTO(c.id, c.usuario, c.total) FROM Carrinho c")
    List<OUTPUT_CarrinhoDTO> findAllCarrinhos();
}

