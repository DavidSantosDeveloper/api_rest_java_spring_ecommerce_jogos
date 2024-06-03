package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;
import com.jogos.ecommerce.domain.dto.input.INPUT_FuncionarioDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_FuncionarioDTO;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Service
public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;
    
    public List<OUTPUT_FuncionarioDTO> ListarFuncionarios(){
        return funcionarioRepository.findAllFuncionarios();
    }
    @Transactional
    public OUTPUT_FuncionarioDTO salvarFuncionario(INPUT_FuncionarioDTO funcionarioDTO){
       var funcionario_sem_id=new Funcionario(funcionarioDTO);
       var funcionario_com_id=funcionarioRepository.save(funcionario_sem_id);
       return new OUTPUT_FuncionarioDTO(funcionario_com_id.getId(),funcionario_com_id.getNome(), funcionario_com_id.getTelefone(), funcionario_sem_id.getDt_nasc(), funcionario_com_id.getCpf(),funcionario_com_id.getSalario(), funcionario_com_id.getCep(), funcionario_com_id.getPais(), funcionario_com_id.getEstado(), funcionario_com_id.getCidade(), funcionario_com_id.getBairro(), funcionario_com_id.getLogradouro(), funcionario_com_id.getNumero(), funcionario_com_id.getPonto_de_referencia(), funcionario_com_id.getCargo());
    }
    @Transactional
    public OUTPUT_FuncionarioDTO editarFuncionario(INPUT_FuncionarioDTO funcionarioDTO){
        var funcionario_sem_id=new Funcionario(funcionarioDTO);
        var funcionario_com_id=funcionarioRepository.save(funcionario_sem_id);
        return new OUTPUT_FuncionarioDTO(funcionario_com_id.getId(),funcionario_com_id.getNome(), funcionario_com_id.getTelefone(), funcionario_sem_id.getDt_nasc(), funcionario_com_id.getCpf(),funcionario_com_id.getSalario(), funcionario_com_id.getCep(), funcionario_com_id.getPais(), funcionario_com_id.getEstado(), funcionario_com_id.getCidade(), funcionario_com_id.getBairro(), funcionario_com_id.getLogradouro(), funcionario_com_id.getNumero(), funcionario_com_id.getPonto_de_referencia(), funcionario_com_id.getCargo());
     }
    @Transactional
    public void excluirFuncionario(Long funcionarioId){
        funcionarioRepository.deleteById(funcionarioId);
    }
}
