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
@RequestMapping("/itens_de_venda")

public class ItemVendaController {
    @Autowired
    private ItemVendaRepository itemVendaRepository;
    @Autowired
    private ItemVendaService itemVendaService;


    @GetMapping("")
    public List<ItemVenda> listar(){
     
        return itemVendaRepository.findAll();
     }

     @GetMapping("/{itemVendaId}")
     public ResponseEntity<ItemVenda> buscarPorId(@PathVariable Long itemVendaId){
        Optional<ItemVenda> pesquisaPeloItemVenda=itemVendaRepository.findById(itemVendaId);

        if(pesquisaPeloItemVenda.isPresent()){
            return ResponseEntity.ok(pesquisaPeloItemVenda.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public ItemVenda cadrastrar(@Valid @RequestBody  ItemVenda itemCarrinho){
        // return itemVendaRepository.save(itemCarrinho);
        return itemVendaService.salvarItemVenda(itemCarrinho);
     }

     @PutMapping("/{itemVendaId}")
     public ResponseEntity<ItemVenda>atualizar(@PathVariable Long itemVendaId,@Valid @RequestBody ItemVenda itemCarrinho){
         if(itemVendaRepository.existsById(itemVendaId)==false){
             return ResponseEntity.notFound().build();
         }
        //  associa o id passado via url ao objeto java criado(que foi criado via dados do corpo body)
         itemCarrinho.setId(itemVendaId);
        //  metodo save verifica existe um itemCarrinho com id informado:Se sim,atualiza os dados.Senão cria um novo registro na tabela itemCarrinho 
        //  itemVendaRepository.save(itemCarrinho);
        itemVendaService.salvarItemVenda(itemCarrinho);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o itemCarrinho
         return ResponseEntity.ok(itemCarrinho);
     }

     @DeleteMapping("/{itemVendaId}")
     public ResponseEntity<Void> deletarItemVenda(@PathVariable Long itemVendaId){
        if(itemVendaRepository.existsById(itemVendaId)==false){
            return ResponseEntity.notFound().build();
        }

        itemVendaRepository.deleteById(itemVendaId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
