package com.jogos.ecommerce.domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.dto.ProdutoDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    
    public ProdutoDTO findById(Long produtoId){
         Optional<Produto> pesquisaPeloProduto=produtoRepository.findById(produtoId);
         if(pesquisaPeloProduto.isEmpty()){
            return null; 
         } 
         Produto produto=pesquisaPeloProduto.get();
         ProdutoDTO produtoDTO=new ProdutoDTO(produtoId,produto.getNome(), produto.getPreco(),produto.getFoto_url(),produto.getDescricao(),produto.getCategoria());
         return produtoDTO;
    }
    public List<ProdutoDTO> ListarProdutos(){
        // return produtoRepository.findAll();
        return produtoRepository.findAllProdutos();
    }
    @Transactional
    public Produto salvarProduto(Produto produto){
       return  produtoRepository.save(produto);
    }
    @Transactional
    public Produto editarProduto(Produto produto){
       return  produtoRepository.save(produto);
    }
    @Transactional
    public void excluirProduto(Long produtoId){
        produtoRepository.deleteById(produtoId);
    }
}
