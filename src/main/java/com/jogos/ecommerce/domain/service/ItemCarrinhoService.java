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
public class ItemCarrinhoService {
    private ItemCarrinhoRepository itemCarrinhoRepository;
    

    public List<ItemCarrinho> ItemCarrinho(ItemCarrinho itemCarrinho){
        return itemCarrinhoRepository.findAll();
    }
    @Transactional
    public ItemCarrinho salvarItemCarrinho(ItemCarrinho itemCarrinho){
       return  itemCarrinhoRepository.save(itemCarrinho);
    }
    @Transactional
    public ItemCarrinho editarItemCarrinho(ItemCarrinho itemCarrinho){
       return  itemCarrinhoRepository.save(itemCarrinho);
    }
    @Transactional
    public void excluirItemCarrinho(Long itemCarrinhoId){
        itemCarrinhoRepository.deleteById(itemCarrinhoId);
    }
}
