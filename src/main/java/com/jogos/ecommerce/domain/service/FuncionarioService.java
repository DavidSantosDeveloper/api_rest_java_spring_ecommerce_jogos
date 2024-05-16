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
public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;
    

    public List<Funcionario> ListarFuncionarios(Funcionario funcionario){
        return funcionarioRepository.findAll();
    }
    @Transactional
    public Funcionario salvarFuncionario(Funcionario funcionario){
       return  funcionarioRepository.save(funcionario);
    }
    @Transactional
    public Funcionario editarFuncionario(Funcionario funcionario){
       return  funcionarioRepository.save(funcionario);
    }
    @Transactional
    public void excluirFuncionario(Long funcionarioId){
        funcionarioRepository.deleteById(funcionarioId);
    }
}
