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
public class ClienteService {
    private ClienteRepository clienteRepository;
    

    public List<Cliente> ListarClientes(Cliente cliente){
        return clienteRepository.findAll();
    }
    @Transactional
    public Cliente salvarCliente(Cliente cliente){
       return  clienteRepository.save(cliente);
    }
    @Transactional
    public Cliente editarCliente(Cliente cliente){
       return  clienteRepository.save(cliente);
    }
    @Transactional
    public void excluirCliente(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
