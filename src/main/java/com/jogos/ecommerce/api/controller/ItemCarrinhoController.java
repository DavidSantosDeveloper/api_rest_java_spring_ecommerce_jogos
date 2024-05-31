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

import com.jogos.ecommerce.domain.dto.input.INPUT_ItemCarrinhoDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_FuncionarioDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ItemCarrinhoDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;
import com.jogos.ecommerce.domain.service.*;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

@AllArgsConstructor        // gera um construtor automaticamente
@RestController
@RequestMapping("/itens_de_carrinho")

public class ItemCarrinhoController {
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;
    @Autowired
    private ItemCarrinhoService itemCarrinhoService;


    @GetMapping("")
    public List<OUTPUT_ItemCarrinhoDTO > listar(){
     
        return itemCarrinhoService.ListarItemCarrinho();
     }

     @GetMapping("/{itemCarrinhoId}")
     public ResponseEntity<OUTPUT_ItemCarrinhoDTO> buscarPorId(@PathVariable Long itemCarrinhoId){
        Optional<OUTPUT_ItemCarrinhoDTO> pesquisaPeloItemCarrinho=Optional.ofNullable(itemCarrinhoService.findById(itemCarrinhoId));

        if(pesquisaPeloItemCarrinho.isPresent()){
            return ResponseEntity.ok(pesquisaPeloItemCarrinho.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_ItemCarrinhoDTO cadrastrar(@Valid @RequestBody  INPUT_ItemCarrinhoDTO itemCarrinhoDTO){
        // return itemCarrinhoRepository.save(itemCarrinho);
        return itemCarrinhoService.salvarItemCarrinho(itemCarrinhoDTO);
     }

     @PutMapping("/{itemCarrinhoId}")
     public ResponseEntity<OUTPUT_ItemCarrinhoDTO>atualizar(@PathVariable Long itemCarrinhoId,@Valid @RequestBody INPUT_ItemCarrinhoDTO itemCarrinhoDTO){
         if(itemCarrinhoRepository.existsById(itemCarrinhoId)==false){
             return ResponseEntity.notFound().build();
         }
         OUTPUT_ItemCarrinhoDTO itemCarrinho_editado=itemCarrinhoService.editarItemCarrinho(itemCarrinhoDTO);
         return ResponseEntity.ok(itemCarrinho_editado);
     }

     @DeleteMapping("/{itemCarrinhoId}")
     public ResponseEntity<Void> deletarItemCarrinho(@PathVariable Long itemCarrinhoId){
        if(itemCarrinhoRepository.existsById(itemCarrinhoId)==false){
            return ResponseEntity.notFound().build();
        }

        itemCarrinhoService.excluirItemCarrinho(itemCarrinhoId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
