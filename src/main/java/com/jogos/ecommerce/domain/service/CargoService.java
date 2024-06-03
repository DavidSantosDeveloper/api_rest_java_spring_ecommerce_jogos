package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.dto.CargoDTO;
import com.jogos.ecommerce.domain.dto.input.INPUT_CargoDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_CargoDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CargoService {
    private CargoRepository cargoRepository;
    
    public OUTPUT_CargoDTO findById(Long cargoId){
        Optional<Cargo> pesquisaPeloCargo=cargoRepository.findById(cargoId);
        if(pesquisaPeloCargo.isEmpty()){
           return null; 
        } 
        Cargo cargo=pesquisaPeloCargo.get();
        OUTPUT_CargoDTO cargoDTO=new OUTPUT_CargoDTO(cargoId,cargo.getNome(),cargo.getDescricao());
        return cargoDTO;
    }
    public List<OUTPUT_CargoDTO> ListarCargos(){
        return cargoRepository.findAllCargos();
    }
    @Transactional
    public OUTPUT_CargoDTO salvarCargo(INPUT_CargoDTO cargoDTO){
       Cargo cargo_sem_id=new Cargo(null,cargoDTO.nome(),cargoDTO.descricao(),null);
       Cargo cargo_com_id=cargoRepository.save(cargo_sem_id);
       return new OUTPUT_CargoDTO(cargo_com_id.getId(), cargo_com_id.getNome(),cargo_com_id.getDescricao());
    }
    @Transactional
    public OUTPUT_CargoDTO editarCargo(INPUT_CargoDTO cargoDTO){
        Cargo cargo_sem_id=new Cargo(null,cargoDTO.nome(),cargoDTO.descricao(),null);
       Cargo cargo_com_id=cargoRepository.save(cargo_sem_id);
       return new OUTPUT_CargoDTO(cargo_com_id.getId(), cargo_com_id.getNome(),cargo_com_id.getDescricao());
    }
    @Transactional
    public void excluirCargo(Long cargoId){
        cargoRepository.deleteById(cargoId);
    }
}
