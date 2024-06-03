package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jogos.ecommerce.domain.dto.input.INPUT_ItemCarrinhoDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ItemCarrinhoDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemCarrinhoService {
    private ItemCarrinhoRepository itemCarrinhoRepository;
    
     public OUTPUT_ItemCarrinhoDTO findById(Long itemCarrinhoId){
         Optional <ItemCarrinho> pesquisaPeloItemCarrinho=itemCarrinhoRepository.findById(itemCarrinhoId);
         if(pesquisaPeloItemCarrinho.isEmpty()){
            return null; 
         } 
         ItemCarrinho itemCarrinho=pesquisaPeloItemCarrinho.get();
         OUTPUT_ItemCarrinhoDTO itemCarrinhoDTO=new OUTPUT_ItemCarrinhoDTO(itemCarrinho.getId(), itemCarrinho.getQuantidade(),itemCarrinho.getPreco_unitario() ,itemCarrinho.getCarrinho(), itemCarrinho.getProduto());
         return itemCarrinhoDTO;
    }

    public List<OUTPUT_ItemCarrinhoDTO> ListarItemCarrinho(){
        return itemCarrinhoRepository.findAllItemCarrinho();
    }
    @Transactional
    public OUTPUT_ItemCarrinhoDTO salvarItemCarrinho(INPUT_ItemCarrinhoDTO itemCarrinhoDTO){
       var itemCarrinho_sem_id=new ItemCarrinho(itemCarrinhoDTO.quantidade(),itemCarrinhoDTO.preco_unitario(),itemCarrinhoDTO.carrinho(),itemCarrinhoDTO.produto());
       var itemCarrinho_com_id=itemCarrinhoRepository.save(itemCarrinho_sem_id);
       return new OUTPUT_ItemCarrinhoDTO(itemCarrinho_com_id.getId(),itemCarrinho_com_id.getQuantidade(),itemCarrinho_com_id.getPreco_unitario(),itemCarrinho_com_id.getCarrinho(),itemCarrinho_com_id.getProduto());
    }
    @Transactional
    public OUTPUT_ItemCarrinhoDTO editarItemCarrinho(INPUT_ItemCarrinhoDTO itemCarrinhoDTO){
        var itemCarrinho_sem_id=new ItemCarrinho(itemCarrinhoDTO.quantidade(),itemCarrinhoDTO.preco_unitario(),itemCarrinhoDTO.carrinho(),itemCarrinhoDTO.produto());
        var itemCarrinho_com_id=itemCarrinhoRepository.save(itemCarrinho_sem_id);
        return new OUTPUT_ItemCarrinhoDTO(itemCarrinho_com_id.getId(),itemCarrinho_com_id.getQuantidade(),itemCarrinho_com_id.getPreco_unitario(),itemCarrinho_com_id.getCarrinho(),itemCarrinho_com_id.getProduto());
     }
    @Transactional
    public void excluirItemCarrinho(Long itemCarrinhoId){
        itemCarrinhoRepository.deleteById(itemCarrinhoId);
    }
}
