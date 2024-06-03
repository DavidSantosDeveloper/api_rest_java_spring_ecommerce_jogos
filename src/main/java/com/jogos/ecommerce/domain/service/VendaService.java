package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.dto.input.INPUT_VendaDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_VendaDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Service
public class VendaService {
    private VendaRepository vendaRepository;
    

    public List<OUTPUT_VendaDTO> ListarVendas(){
        return vendaRepository.findAllVendas();
    }
    @Transactional
    public OUTPUT_VendaDTO salvarVenda(INPUT_VendaDTO vendaDTO){
        Venda venda_sem_id=new Venda(null,vendaDTO.dt_venda(),vendaDTO.valor_total(),vendaDTO.usuario(),null);
        Venda venda_com_id=vendaRepository.save(venda_sem_id);
        return new OUTPUT_VendaDTO(venda_com_id.getId(), venda_com_id.getDt_venda(), venda_com_id.getValor_total(),venda_com_id.getUsuario());
    }
    @Transactional
    public OUTPUT_VendaDTO editarVenda(INPUT_VendaDTO vendaDTO){
        Venda venda_sem_id=new Venda(null,vendaDTO.dt_venda(),vendaDTO.valor_total(),vendaDTO.usuario(),null);
        Venda venda_com_id=vendaRepository.save(venda_sem_id);
        return new OUTPUT_VendaDTO(venda_com_id.getId(), venda_com_id.getDt_venda(), venda_com_id.getValor_total(),venda_com_id.getUsuario());
    }
    @Transactional
    public void excluirVenda(Long vendaId){
        vendaRepository.deleteById(vendaId);
    }
}
