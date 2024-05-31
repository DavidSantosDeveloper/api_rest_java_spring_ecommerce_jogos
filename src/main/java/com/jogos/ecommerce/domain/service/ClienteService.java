package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.dto.ClienteDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClienteService {
    private ClienteRepository clienteRepository;
    
    public ClienteDTO findById(Long clienteId){
         Optional <Cliente> pesquisaPeloCliente=clienteRepository.findById(clienteId);
         if(pesquisaPeloCliente.isEmpty()){
            return null; 
         } 
         Cliente cliente=pesquisaPeloCliente.get();
         ClienteDTO clienteDTO=new ClienteDTO(clienteId,cliente.getNome(),cliente.getTelefone(),cliente.getDt_nasc(),cliente.getCpf(),cliente.getCep(),cliente.getPais(),cliente.getEstado(),cliente.getCidade(),cliente.getBairro(),cliente.getLogradouro(),cliente.getNumero(),cliente.getPonto_de_referencia());
         return clienteDTO;
    }

    public List<ClienteDTO> ListarClientes(){
        return clienteRepository.findAllClientes();
    }
    @Transactional
    public Cliente salvarCliente(ClienteDTO clienteDTO){
        Cliente cliente=new Cliente(clienteDTO.id(),clienteDTO);
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
