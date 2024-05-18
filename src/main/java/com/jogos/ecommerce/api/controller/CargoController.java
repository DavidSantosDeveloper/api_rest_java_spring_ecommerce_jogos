package com.jogos.ecommerce.api.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Cargo> listar(){
     
        return cargoRepository.findAll();
     }

     @GetMapping("/{cargoId}")
     public ResponseEntity<Cargo> buscarPorId(@PathVariable Long cargoId){
        Optional<Cargo> pesquisaPeloCargo=cargoRepository.findById(cargoId);

        if(pesquisaPeloCargo.isPresent()){
            return ResponseEntity.ok(pesquisaPeloCargo.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public Cargo cadrastrar(@Valid @RequestBody  Cargo cliente){
        // return cargoRepository.save(cliente);
        return cargoService.salvarCargo(cliente);
     }

     @PutMapping("/{cargoId}")
     public ResponseEntity<Cargo>atualizar(@PathVariable Long cargoId,@Valid @RequestBody Cargo cliente){
         if(cargoRepository.existsById(cargoId)==false){
             return ResponseEntity.notFound().build();
         }
        //  associa o id passado via url ao objeto java criado(que foi criado via dados do corpo body)
         cliente.setId(cargoId);
        //  metodo save verifica existe um cliente com id informado:Se sim,atualiza os dados.Senão cria um novo registro na tabela cliente 
        //  cargoRepository.save(cliente);
        cargoService.salvarCargo(cliente);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o cliente
         return ResponseEntity.ok(cliente);
     }

     @DeleteMapping("/{cargoId}")
     public ResponseEntity<Void> deletarCliente(@PathVariable Long cargoId){
        if(cargoRepository.existsById(cargoId)==false){
            return ResponseEntity.notFound().build();
        }

        cargoRepository.deleteById(cargoId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
