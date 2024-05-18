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
@RequestMapping("/vendas")

public class VendaController {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private VendaService vendaService;


    @GetMapping("")
    public List<Venda> listar(){
     
        return vendaRepository.findAll();
     }

     @GetMapping("/{vendaId}")
     public ResponseEntity<Venda> buscarPorId(@PathVariable Long vendaId){
        Optional<Venda> pesquisaPeloVenda=vendaRepository.findById(vendaId);

        if(pesquisaPeloVenda.isPresent()){
            return ResponseEntity.ok(pesquisaPeloVenda.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public Venda cadrastrar(@Valid @RequestBody  Venda venda){
        // return vendaRepository.save(venda);
        return vendaService.salvarVenda(venda);
     }

     @PutMapping("/{vendaId}")
     public ResponseEntity<Venda>atualizar(@PathVariable Long vendaId,@Valid @RequestBody Venda venda){
         if(vendaRepository.existsById(vendaId)==false){
             return ResponseEntity.notFound().build();
         }
        //  associa o id passado via url ao objeto java criado(que foi criado via dados do corpo body)
         venda.setId(vendaId);
        //  metodo save verifica existe um venda com id informado:Se sim,atualiza os dados.Senão cria um novo registro na tabela venda 
        //  vendaRepository.save(venda);
        vendaService.salvarVenda(venda);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o venda
         return ResponseEntity.ok(venda);
     }

     @DeleteMapping("/{vendaId}")
     public ResponseEntity<Void> deletarVenda(@PathVariable Long vendaId){
        if(vendaRepository.existsById(vendaId)==false){
            return ResponseEntity.notFound().build();
        }

        vendaRepository.deleteById(vendaId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
