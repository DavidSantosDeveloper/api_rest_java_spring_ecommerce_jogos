package com.jogos.ecommerce.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jogos.ecommerce.domain.dto.output.OUTPUT_FuncionarioDTO;
import com.jogos.ecommerce.domain.model.*;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    Funcionario findById(long id);

     @Query("SELECT new com.jogos.ecommerce.domain.dto.output.OUTPUT_FuncionarioDTO(f.id,f.nome,f.dt_nasc,f.cpf,f.salario,f.cep,f.pais,f.estado,f.cidade,f.bairro,f.logradouro,f.numero,f.ponto_de_referencia) FROM Funcionario f")
    List<OUTPUT_FuncionarioDTO> findAllFuncionarios();
}

