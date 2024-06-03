package com.jogos.ecommerce.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jogos.ecommerce.domain.dto.output.OUTPUT_ClienteDTO;
import com.jogos.ecommerce.domain.model.*;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente findById(long id);

    //os campos c.id,... se referem aos campos do objeto DTO, ja c é o apelido da tabela
    @Query("SELECT new com.jogos.ecommerce.domain.dto.output.OUTPUT_ClienteDTO(c.id, c.nome, c.telefone,c.dt_nasc,c.cpf,c.cep,c.pais,c.estado,c.cidade,c.bairro,c.logradouro,c.numero,c.ponto_de_referencia) FROM Cliente c")
    List<OUTPUT_ClienteDTO> findAllClientes();
}

