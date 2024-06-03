package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;
import com.jogos.ecommerce.domain.dto.*;
import com.jogos.ecommerce.domain.dto.input.INPUT_CarrinhoDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_CarrinhoDTO;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CarrinhoService {
    private CarrinhoRepository carrinhoRepository;
    
    public OUTPUT_CarrinhoDTO findById(Long carrrinhoId){
        Optional<Carrinho> BuscaPeloCarrinho=carrinhoRepository.findById(carrrinhoId);
        if(BuscaPeloCarrinho.isEmpty()){
            return null;
        } 
        Carrinho carrinho=BuscaPeloCarrinho.get();
        OUTPUT_CarrinhoDTO carrinhoDTO=new OUTPUT_CarrinhoDTO(carrinho.getId(),carrinho.getUsuario(),carrinho.getTotal());
        return carrinhoDTO;
    }
    public List<OUTPUT_CarrinhoDTO> ListarCarrinhos(){
        return carrinhoRepository.findAllCarrinhos();
    }
    @Transactional
    public OUTPUT_CarrinhoDTO salvarCarrinho(INPUT_CarrinhoDTO carrinhoDTO){
       Carrinho carrinho_sem_id=new Carrinho(null,carrinhoDTO.usuario(),carrinhoDTO.total(),null);
       Carrinho carrinho_com_id=carrinhoRepository.save(carrinho_sem_id);
       return  new OUTPUT_CarrinhoDTO(carrinho_com_id.getId(), carrinho_com_id.getUsuario(), carrinho_com_id.getTotal());
    }
    @Transactional
    public OUTPUT_CarrinhoDTO editarCarrinho(INPUT_CarrinhoDTO carrinhoDTO){
        Carrinho carrinho_sem_id=new Carrinho(null,carrinhoDTO.usuario(),carrinhoDTO.total(),null);
        Carrinho carrinho_com_id=carrinhoRepository.save(carrinho_sem_id);
        return  new OUTPUT_CarrinhoDTO(carrinho_com_id.getId(), carrinho_com_id.getUsuario(), carrinho_com_id.getTotal());
     }
    @Transactional
    public void excluirCarrinho(Long carrinhoId){
        carrinhoRepository.deleteById(carrinhoId);
    }
}
