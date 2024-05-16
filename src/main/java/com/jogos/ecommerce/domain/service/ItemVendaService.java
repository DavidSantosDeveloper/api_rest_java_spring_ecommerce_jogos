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
public class ItemVendaService {
    private ItemVendaRepository itemVendaRepository;
    

    public List<ItemVenda> ListarItemVenda(ItemVenda itemVenda){
        return itemVendaRepository.findAll();
    }
    @Transactional
    public ItemVenda salvarItemVenda(ItemVenda itemVenda){
       return  itemVendaRepository.save(itemVenda);
    }
    @Transactional
    public ItemVenda editarItemVenda(ItemVenda itemVenda){
       return  itemVendaRepository.save(itemVenda);
    }
    @Transactional
    public void excluirItemVenda(Long ItemVendaId){
        itemVendaRepository.deleteById(ItemVendaId);
    }
}
