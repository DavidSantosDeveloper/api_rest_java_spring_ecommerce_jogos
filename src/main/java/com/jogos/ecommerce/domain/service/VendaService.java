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
public class VendaService {
    private VendaRepository vendaRepository;
    

    public List<Venda> ListarVenda(Venda venda){
        return vendaRepository.findAll();
    }
    @Transactional
    public Venda salvarVenda(Venda venda){
       return  vendaRepository.save(venda);
    }
    @Transactional
    public Venda editarVenda(Venda venda){
       return  vendaRepository.save(venda);
    }
    @Transactional
    public void excluirVenda(Long vendaId){
        vendaRepository.deleteById(vendaId);
    }
}
