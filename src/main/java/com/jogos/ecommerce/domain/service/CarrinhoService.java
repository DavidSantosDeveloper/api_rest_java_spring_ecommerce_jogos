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
public class CarrinhoService {
    private CarrinhoRepository carrinhoRepository;
    

    public List<Carrinho> ListarCarrinhos(Carrinho carrinho){
        return carrinhoRepository.findAll();
    }
    @Transactional
    public Carrinho salvarCarrinho(Carrinho carrinho){
       return  carrinhoRepository.save(carrinho);
    }
    @Transactional
    public Carrinho editarCarrinho(Carrinho carrinho){
       return  carrinhoRepository.save(carrinho);
    }
    @Transactional
    public void excluirCarrinho(Long carrinhoId){
        carrinhoRepository.deleteById(carrinhoId);
    }
}
