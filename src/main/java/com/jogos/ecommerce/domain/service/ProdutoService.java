package com.jogos.ecommerce.domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.dto.input.INPUT_ProdutoDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ProdutoDTO;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    
    public OUTPUT_ProdutoDTO findById(Long produtoId){
         Optional<Produto> pesquisaPeloProduto=produtoRepository.findById(produtoId);
         if(pesquisaPeloProduto.isEmpty()){
            return null; 
         } 
         Produto produto=pesquisaPeloProduto.get();
         OUTPUT_ProdutoDTO produtoDTO=new OUTPUT_ProdutoDTO(produtoId,produto.getNome(), produto.getPreco(),produto.getFoto_url(),produto.getDescricao(),produto.getCategoria());
         return produtoDTO;
    }
    public List<OUTPUT_ProdutoDTO> ListarProdutos(){
        // return produtoRepository.findAll();
        return produtoRepository.findAllProdutos();
    }
    @Transactional
    public OUTPUT_ProdutoDTO salvarProduto(INPUT_ProdutoDTO produtoDTO){
        Produto produto_sem_id=new Produto(null,produtoDTO.nome(),produtoDTO.preco(),produtoDTO.foto_url(),produtoDTO.descricao(),produtoDTO.categoria(),null,null);
        Produto produto_com_id=produtoRepository.save(produto_sem_id);
        return new OUTPUT_ProdutoDTO(produto_com_id.getId(), produto_com_id.getNome(), produto_com_id.getPreco(), produto_com_id.getFoto_url(), produto_com_id.getDescricao(),produto_com_id.getCategoria());
    }
    @Transactional
    public OUTPUT_ProdutoDTO editarProduto(INPUT_ProdutoDTO produtoDTO){
        Produto produto_sem_id=new Produto(null,produtoDTO.nome(),produtoDTO.preco(),produtoDTO.foto_url(),produtoDTO.descricao(),produtoDTO.categoria(),null,null);
        Produto produto_com_id=produtoRepository.save(produto_sem_id);
        return new OUTPUT_ProdutoDTO(produto_com_id.getId(), produto_com_id.getNome(), produto_com_id.getPreco(), produto_com_id.getFoto_url(), produto_com_id.getDescricao(),produto_com_id.getCategoria());
    }
    @Transactional
    public void excluirProduto(Long produtoId){
        produtoRepository.deleteById(produtoId);
    }
}
