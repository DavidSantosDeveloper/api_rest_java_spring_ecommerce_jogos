package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;
import com.jogos.ecommerce.domain.dto.*;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CarrinhoService {
    private CarrinhoRepository carrinhoRepository;
    
    public CarrinhoDTO findById(Long carrrinhoId){
        Optional<Carrinho> BuscaPeloCarrinho=carrinhoRepository.findById(carrrinhoId);
        if(BuscaPeloCarrinho.isEmpty()){
            return null;
        } 
        Carrinho carrinho=BuscaPeloCarrinho.get();
        CarrinhoDTO carrinhoDTO=new CarrinhoDTO(carrinho.getId(),carrinho.getUsuario(),carrinho.getTotal());
        return carrinhoDTO;
    }
    public List<CarrinhoDTO> ListarCarrinhos(){
        return carrinhoRepository.findAllCarrinhos();
    }
    @Transactional
    public Carrinho salvarCarrinho(CarrinhoDTO carrinhoDTO){
       Carrinho carrinho=new Carrinho(carrinhoDTO);
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
