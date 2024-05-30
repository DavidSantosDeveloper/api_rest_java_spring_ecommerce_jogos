package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.dto.CargoDTO;
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
    
    public CargoDTO findById(Long cargoId){
        Optional<Cargo> pesquisaPeloCargo=cargoRepository.findById(cargoId);
        if(pesquisaPeloCargo.isEmpty()){
           return null; 
        } 
        Cargo cargo=pesquisaPeloCargo.get();
        CargoDTO cargoDTO=new CargoDTO(cargoId,cargo.getNome(),cargo.getDescricao());
        return cargoDTO;
    }
    public List<CargoDTO> ListarCargos(){
        return cargoRepository.findAllCargos();
    }
    @Transactional
    public Cargo salvarCargo(CargoDTO cargoDTO){
       Cargo cargo=new Cargo(cargoDTO);
       return  cargoRepository.save(cargo);
    }
    @Transactional
    public Cargo editarCargo(Cargo cargo){
       return  cargoRepository.save(cargo);
    }
    @Transactional
    public void excluirCargo(Long cargoId){
        cargoRepository.deleteById(cargoId);
    }
}
