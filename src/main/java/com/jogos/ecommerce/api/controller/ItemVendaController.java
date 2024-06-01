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

import com.jogos.ecommerce.domain.dto.input.INPUT_ItemVenda_DTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ItemVenda_DTO;
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
     public ResponseEntity<OUTPUT_ItemVenda_DTO> buscarPorId(@PathVariable Long itemVendaId){
        Optional<OUTPUT_ItemVenda_DTO> pesquisaPeloItemVenda=Optional.ofNullable(itemVendaService.findById(itemVendaId));

        if(pesquisaPeloItemVenda.isPresent()){
            return ResponseEntity.ok(pesquisaPeloItemVenda.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_ItemVenda_DTO cadrastrar(@Valid @RequestBody INPUT_ItemVenda_DTO itemVendaDTO){
        // return itemVendaRepository.save(itemCarrinho);
        return itemVendaService.salvarItemVenda(itemVendaDTO);
     }

     @PutMapping("/{itemVendaId}")
     public ResponseEntity<OUTPUT_ItemVenda_DTO>atualizar(@PathVariable Long itemVendaId,@Valid @RequestBody INPUT_ItemVenda_DTO itemCarrinhoDTO){
         if(itemVendaRepository.existsById(itemVendaId)==false){
             return ResponseEntity.notFound().build();
         }
         OUTPUT_ItemVenda_DTO itemVenda_editado=itemVendaService.editarItemVenda(itemCarrinhoDTO);
         return ResponseEntity.ok(itemVenda_editado);
     }

     @DeleteMapping("/{itemVendaId}")
     public ResponseEntity<Void> deletarItemVenda(@PathVariable Long itemVendaId){
        if(itemVendaRepository.existsById(itemVendaId)==false){
            return ResponseEntity.notFound().build();
        }

        itemVendaService.excluirItemVenda(itemVendaId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
