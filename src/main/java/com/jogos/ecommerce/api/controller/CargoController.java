package com.jogos.ecommerce.api.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jogos.ecommerce.domain.dto.input.INPUT_CargoDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_CargoDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;
import com.jogos.ecommerce.domain.service.*;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

@AllArgsConstructor        // gera um construtor automaticamente
@RestController
@RequestMapping("/cargos")
public class CargoController {
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private CargoService cargoService;


    @GetMapping("")
    public List<OUTPUT_CargoDTO> listar(){
     
        return cargoService.ListarCargos();
     }

     @GetMapping("/{cargoId}")
     public ResponseEntity<OUTPUT_CargoDTO> buscarPorId(@PathVariable Long cargoId){
        Optional<OUTPUT_CargoDTO> pesquisaPeloCargo=Optional.ofNullable(cargoService.findById(cargoId));

        if(pesquisaPeloCargo.isPresent()){
            return ResponseEntity.ok(pesquisaPeloCargo.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_CargoDTO cadrastrar(@Valid @RequestBody  INPUT_CargoDTO cargoDTO){

        return cargoService.salvarCargo(cargoDTO);
     }

     @PutMapping("/{cargoId}")
     public ResponseEntity<OUTPUT_CargoDTO>atualizar(@PathVariable Long cargoId,@Valid @RequestBody INPUT_CargoDTO cargoDTO){
         if(cargoRepository.existsById(cargoId)==false){
             return ResponseEntity.notFound().build();
         }
         OUTPUT_CargoDTO cargo_editado=cargoService.editarCargo(cargoDTO);
         return ResponseEntity.ok(cargo_editado);
     }
     @PatchMapping("/{cargoId}")
     public ResponseEntity<OUTPUT_CargoDTO>atualizarParcial(@PathVariable Long cargoId,@Valid @RequestBody INPUT_CargoDTO cargoDTO){
        if(cargoRepository.existsById(cargoId)==false){
            return ResponseEntity.notFound().build();
        }
     
        OUTPUT_CargoDTO cargo_editado=cargoService.editarCargo(cargoDTO);
        return ResponseEntity.ok(cargo_editado);
    }

     @DeleteMapping("/{cargoId}")
     public ResponseEntity<Void> deletarCliente(@PathVariable Long cargoId){
        if(cargoRepository.existsById(cargoId)==false){
           return ResponseEntity.notFound().build();
        }

        cargoService.excluirCargo(cargoId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
