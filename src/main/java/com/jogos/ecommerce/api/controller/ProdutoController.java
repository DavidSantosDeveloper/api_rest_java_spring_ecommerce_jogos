package com.jogos.ecommerce.api.controller;

import java.util.ArrayList;
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
import com.jogos.ecommerce.domain.dto.*;
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
    public List<ProdutoDTO> listar(){
        List<Produto> produtos=produtoRepository.findAll();
        //conversao para  o para produtoDTO
        List<ProdutoDTO> produtosDTO=new ArrayList<ProdutoDTO>();
        for(int i=0;i< produtos.size();i++){
            Produto produto=produtos.get(i);
            ProdutoDTO produtoDTO=new ProdutoDTO(produto.getId(),produto.getNome(),produto.getPreco(),produto.getFoto_url(),produto.getDescricao(),produto.getCategoria());
            produtosDTO.add(produtoDTO);  
        }

        return produtosDTO;
     } 

     @GetMapping("/{produtoId}")
     public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long produtoId){
        Optional<Produto> pesquisaPeloProduto=produtoRepository.findById(produtoId);

        if(pesquisaPeloProduto.isPresent()){
            Produto produto=pesquisaPeloProduto.get();
            ProdutoDTO produtoDTO=new ProdutoDTO(produtoId,produto.getNome(), produto.getPreco(),produto.getFoto_url(),produto.getDescricao(),produto.getCategoria());
            return ResponseEntity.ok(produtoDTO); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public Produto cadrastrar(@Valid @RequestBody  ProdutoDTO produtoDTO){
        Produto produto=new Produto(produtoDTO);
        // return produtoRepository.save(produto);
        return produtoService.salvarProduto(produto);
     }

     @PutMapping("/{produtoId}")
     public ResponseEntity<ProdutoDTO>atualizar(@PathVariable Long produtoId,@Valid @RequestBody ProdutoDTO produtoDTO){
         if(produtoRepository.existsById(produtoId)==false){
             return ResponseEntity.notFound().build();
         }
         Produto produto=new Produto(produtoId,produtoDTO);
        produtoService.salvarProduto(produto);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o produto
         return ResponseEntity.ok(produtoDTO);
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
