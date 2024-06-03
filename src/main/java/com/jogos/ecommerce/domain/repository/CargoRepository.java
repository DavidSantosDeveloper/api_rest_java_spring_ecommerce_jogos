package com.jogos.ecommerce.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jogos.ecommerce.domain.dto.CargoDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_CargoDTO;
import com.jogos.ecommerce.domain.model.*;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,Long> {
    Cargo findById(long id);

     @Query("SELECT new com.jogos.ecommerce.domain.dto.CargoDTO(c.id, c.nome,c.descricao) FROM Cargo c")
    List<OUTPUT_CargoDTO> findAllCargos();
}
