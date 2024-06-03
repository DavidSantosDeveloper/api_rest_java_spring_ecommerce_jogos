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

import com.jogos.ecommerce.domain.dto.input.INPUT_ProdutoDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ProdutoDTO;
import com.jogos.ecommerce.domain.repository.*;
import com.jogos.ecommerce.domain.service.*;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor        // gera um construtor automaticamente
@RestController
@RequestMapping("/produtos")

public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("")
    public List<OUTPUT_ProdutoDTO> listar(){
    
        return produtoService.ListarProdutos();
     } 

     @GetMapping("/{produtoId}")
     public ResponseEntity<OUTPUT_ProdutoDTO> buscarPorId(@PathVariable Long produtoId){
        Optional<OUTPUT_ProdutoDTO> pesquisaPeloProduto=Optional.ofNullable(produtoService.findById(produtoId));
        if(pesquisaPeloProduto.isPresent()){
            return ResponseEntity.ok(pesquisaPeloProduto.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
        

     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_ProdutoDTO cadrastrar(@Valid @RequestBody INPUT_ProdutoDTO produtoDTO){
       
        return produtoService.salvarProduto(produtoDTO);
     }

     @PutMapping("/{produtoId}")
     public ResponseEntity<OUTPUT_ProdutoDTO>atualizar(@PathVariable Long produtoId,@Valid @RequestBody INPUT_ProdutoDTO produtoDTO){
         if(produtoRepository.existsById(produtoId)==false){
             return ResponseEntity.notFound().build();
         }
        OUTPUT_ProdutoDTO produto_editado=produtoService.editarProduto(produtoDTO);
         return ResponseEntity.ok(produto_editado);
     }

     @DeleteMapping("/{produtoId}")
     public ResponseEntity<Void> deletarItemVenda(@PathVariable Long produtoId){
        if(produtoRepository.existsById(produtoId)==false){
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(produtoId);

        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
