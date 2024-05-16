package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Service
public class CargoService {
    private CargoRepository cargoRepository;
    

    public List<Cargo> ListarCargos(Cargo cargo){
        return cargoRepository.findAll();
    }
    @Transactional
    public Cargo salvarCargo(Cargo cargo){
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
