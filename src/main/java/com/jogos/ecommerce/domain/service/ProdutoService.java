package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    

    public List<Produto> ListarProdutos(Produto produto){
        return produtoRepository.findAll();
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
