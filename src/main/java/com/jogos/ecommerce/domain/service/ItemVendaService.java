package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.dto.input.INPUT_ItemVenda_DTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ItemVenda_DTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemVendaService {
    private ItemVendaRepository itemVendaRepository;
    
    public OUTPUT_ItemVenda_DTO findById(Long itemVendaId){
        Optional<ItemVenda> buscaPeloItemVenda=itemVendaRepository.findById(itemVendaId);
        if (buscaPeloItemVenda.isEmpty()) {
            return null;
        }
        ItemVenda itemCarrinho=buscaPeloItemVenda.get();
        return new OUTPUT_ItemVenda_DTO(itemCarrinho.getId(), itemCarrinho.getQuantidade(), itemCarrinho.getPreco_unitario(), itemCarrinho.getVenda(), itemCarrinho.getProduto());

    }
    public List<OUTPUT_ItemVenda_DTO> ListarItemVenda(){
        return itemVendaRepository.findAllItemVenda();
    }
    @Transactional
    public OUTPUT_ItemVenda_DTO salvarItemVenda(INPUT_ItemVenda_DTO itemVendaDTO){
       ItemVenda itemVenda_sem_id=new ItemVenda(itemVendaDTO.quantidade(),itemVendaDTO.preco_unitario(),itemVendaDTO.venda(),itemVendaDTO.produto());
       var itemVenda_com_id= itemVendaRepository.save(itemVenda_sem_id);
        return new OUTPUT_ItemVenda_DTO(itemVenda_com_id.getId(), itemVenda_com_id.getQuantidade(), itemVenda_com_id.getPreco_unitario(), itemVenda_com_id.getVenda(),itemVenda_com_id.getProduto());
    }
    @Transactional
    public OUTPUT_ItemVenda_DTO editarItemVenda(INPUT_ItemVenda_DTO itemVendaDTO){
        ItemVenda itemVenda_sem_id=new ItemVenda(itemVendaDTO.quantidade(),itemVendaDTO.preco_unitario(),itemVendaDTO.venda(),itemVendaDTO.produto());
        var itemVenda_com_id= itemVendaRepository.save(itemVenda_sem_id);
         return new OUTPUT_ItemVenda_DTO(itemVenda_com_id.getId(), itemVenda_com_id.getQuantidade(), itemVenda_com_id.getPreco_unitario(), itemVenda_com_id.getVenda(),itemVenda_com_id.getProduto());
     }
    @Transactional
    public void excluirItemVenda(Long ItemVendaId){
        itemVendaRepository.deleteById(ItemVendaId);
    }
}
