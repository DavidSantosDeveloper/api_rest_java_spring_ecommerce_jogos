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
@RequestMapping("/itens_de_carrinho")

public class ItemCarrinhoController {
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;
    @Autowired
    private ItemCarrinhoService itemCarrinhoService;


    @GetMapping("")
    public List<ItemCarrinho> listar(){
     
        return itemCarrinhoRepository.findAll();
     }

     @GetMapping("/{itemCarrinhoId}")
     public ResponseEntity<ItemCarrinho> buscarPorId(@PathVariable Long itemCarrinhoId){
        Optional<ItemCarrinho> pesquisaPeloItemCarrinho=itemCarrinhoRepository.findById(itemCarrinhoId);

        if(pesquisaPeloItemCarrinho.isPresent()){
            return ResponseEntity.ok(pesquisaPeloItemCarrinho.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public ItemCarrinho cadrastrar(@Valid @RequestBody  ItemCarrinho itemCarrinho){
        // return itemCarrinhoRepository.save(itemCarrinho);
        return itemCarrinhoService.salvarItemCarrinho(itemCarrinho);
     }

     @PutMapping("/{itemCarrinhoId}")
     public ResponseEntity<ItemCarrinho>atualizar(@PathVariable Long itemCarrinhoId,@Valid @RequestBody ItemCarrinho itemCarrinho){
         if(itemCarrinhoRepository.existsById(itemCarrinhoId)==false){
             return ResponseEntity.notFound().build();
         }
        //  associa o id passado via url ao objeto java criado(que foi criado via dados do corpo body)
         itemCarrinho.setId(itemCarrinhoId);
        //  metodo save verifica existe um itemCarrinho com id informado:Se sim,atualiza os dados.Senão cria um novo registro na tabela itemCarrinho 
        //  itemCarrinhoRepository.save(itemCarrinho);
        itemCarrinhoService.salvarItemCarrinho(itemCarrinho);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o itemCarrinho
         return ResponseEntity.ok(itemCarrinho);
     }

     @DeleteMapping("/{itemCarrinhoId}")
     public ResponseEntity<Void> deletarItemCarrinho(@PathVariable Long itemCarrinhoId){
        if(itemCarrinhoRepository.existsById(itemCarrinhoId)==false){
            return ResponseEntity.notFound().build();
        }

        itemCarrinhoRepository.deleteById(itemCarrinhoId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
