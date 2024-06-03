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
import com.jogos.ecommerce.domain.dto.input.INPUT_CarrinhoDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_CarrinhoDTO;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

@AllArgsConstructor        // gera um construtor automaticamente
@RestController
@RequestMapping("/carrinhos")

public class CarrinhoController {
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private CarrinhoService carrinhoService;


    @GetMapping("")
    public List<OUTPUT_CarrinhoDTO> listar(){
     
        return carrinhoService.ListarCarrinhos();
     }

     @GetMapping("/{carrrinhoId}")
     public ResponseEntity<OUTPUT_CarrinhoDTO> buscarPorId(@PathVariable Long carrrinhoId){
        Optional<OUTPUT_CarrinhoDTO> pesquisaPeloCarrinho=Optional.ofNullable(carrinhoService.findById(carrrinhoId));

        if(pesquisaPeloCarrinho.isPresent()){
            return ResponseEntity.ok(pesquisaPeloCarrinho.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_CarrinhoDTO cadrastrar(@Valid @RequestBody  INPUT_CarrinhoDTO carrinhoDTO){
        // return carrinhoRepository.save(cliente);
        return carrinhoService.salvarCarrinho(carrinhoDTO);
     }

     @PutMapping("/{carrrinhoId}")
     public ResponseEntity<OUTPUT_CarrinhoDTO>atualizar(@PathVariable Long carrinhoId,@Valid @RequestBody INPUT_CarrinhoDTO carrinhoDTO){
      
        if(carrinhoRepository.existsById(carrinhoId)==false){
            return ResponseEntity.notFound().build();
        }

        OUTPUT_CarrinhoDTO carrinho_editado= carrinhoService.editarCarrinho(carrinhoDTO);
        return ResponseEntity.ok(carrinho_editado);
     }

     @DeleteMapping("/{carrrinhoId}")
     public ResponseEntity<Void> deletarCliente(@PathVariable Long carrrinhoId){
        if(carrinhoRepository.existsById(carrrinhoId)==false){
            return ResponseEntity.notFound().build();
        }

        carrinhoService.excluirCarrinho(carrrinhoId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
