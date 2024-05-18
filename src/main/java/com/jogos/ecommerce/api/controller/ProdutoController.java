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
@RequestMapping("/produtos")

public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;


    @GetMapping("")
    public List<Produto> listar(){
     
        return produtoRepository.findAll();
     }

     @GetMapping("/{produtoId}")
     public ResponseEntity<Produto> buscarPorId(@PathVariable Long produtoId){
        Optional<Produto> pesquisaPeloProduto=produtoRepository.findById(produtoId);

        if(pesquisaPeloProduto.isPresent()){
            return ResponseEntity.ok(pesquisaPeloProduto.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public Produto cadrastrar(@Valid @RequestBody  Produto produto){
        // return produtoRepository.save(produto);
        return produtoService.salvarProduto(produto);
     }

     @PutMapping("/{produtoId}")
     public ResponseEntity<Produto>atualizar(@PathVariable Long produtoId,@Valid @RequestBody Produto produto){
         if(produtoRepository.existsById(produtoId)==false){
             return ResponseEntity.notFound().build();
         }
        //  associa o id passado via url ao objeto java criado(que foi criado via dados do corpo body)
         produto.setId(produtoId);
        //  metodo save verifica existe um produto com id informado:Se sim,atualiza os dados.Senão cria um novo registro na tabela produto 
        //  produtoRepository.save(produto);
        produtoService.salvarProduto(produto);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o produto
         return ResponseEntity.ok(produto);
     }

     @DeleteMapping("/{produtoId}")
     public ResponseEntity<Void> deletarItemVenda(@PathVariable Long produtoId){
        if(produtoRepository.existsById(produtoId)==false){
            return ResponseEntity.notFound().build();
        }

        produtoRepository.deleteById(produtoId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
