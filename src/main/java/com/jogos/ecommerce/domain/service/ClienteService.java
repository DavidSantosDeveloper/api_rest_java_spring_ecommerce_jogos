package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.dto.input.INPUT_ClienteDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ClienteDTO;

import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClienteService {
    private ClienteRepository clienteRepository;
    
    public OUTPUT_ClienteDTO findById(Long clienteId){
         Optional <Cliente> pesquisaPeloCliente=clienteRepository.findById(clienteId);
         if(pesquisaPeloCliente.isEmpty()){
            return null; 
         } 
         Cliente cliente=pesquisaPeloCliente.get();
         OUTPUT_ClienteDTO clienteDTO=new OUTPUT_ClienteDTO(clienteId,cliente.getNome(),cliente.getTelefone(),cliente.getDt_nasc(),cliente.getCpf(),cliente.getCep(),cliente.getPais(),cliente.getEstado(),cliente.getCidade(),cliente.getBairro(),cliente.getLogradouro(),cliente.getNumero(),cliente.getPonto_de_referencia());
         return clienteDTO;
    }

    public List<OUTPUT_ClienteDTO> ListarClientes(){
        return clienteRepository.findAllClientes();
    }
    @Transactional
    public OUTPUT_ClienteDTO salvarCliente(INPUT_ClienteDTO clienteDTO){
        Cliente cliente_sem_id=new Cliente(null,clienteDTO.nome(),clienteDTO.telefone(),clienteDTO.dt_nasc(),clienteDTO.cpf(),clienteDTO.cep(),clienteDTO.pais(),clienteDTO.estado(),clienteDTO.cidade(),clienteDTO.bairro(),clienteDTO.logradouro(),clienteDTO.numero(),clienteDTO.ponto_de_referencia(),null);
        Cliente cliente_com_id=clienteRepository.save(cliente_sem_id);
       return  new OUTPUT_ClienteDTO(cliente_com_id.getId(), cliente_com_id.getNome(), cliente_com_id.getTelefone(), cliente_com_id.getDt_nasc(), cliente_com_id.getCpf(), cliente_com_id.getCep(), cliente_com_id.getPais(), cliente_com_id.getEstado(), cliente_com_id.getCidade(), cliente_com_id.getBairro(), cliente_com_id.getLogradouro(), cliente_com_id.getNumero(), cliente_com_id.getPonto_de_referencia());
    }
    @Transactional
    public OUTPUT_ClienteDTO editarCliente(INPUT_ClienteDTO clienteDTO){
        Cliente cliente_sem_id=new Cliente(null,clienteDTO.nome(),clienteDTO.telefone(),clienteDTO.dt_nasc(),clienteDTO.cpf(),clienteDTO.cep(),clienteDTO.pais(),clienteDTO.estado(),clienteDTO.cidade(),clienteDTO.bairro(),clienteDTO.logradouro(),clienteDTO.numero(),clienteDTO.ponto_de_referencia(),null);
        Cliente cliente_com_id=clienteRepository.save(cliente_sem_id);
       return  new OUTPUT_ClienteDTO(cliente_com_id.getId(), cliente_com_id.getNome(), cliente_com_id.getTelefone(), cliente_com_id.getDt_nasc(), cliente_com_id.getCpf(), cliente_com_id.getCep(), cliente_com_id.getPais(), cliente_com_id.getEstado(), cliente_com_id.getCidade(), cliente_com_id.getBairro(), cliente_com_id.getLogradouro(), cliente_com_id.getNumero(), cliente_com_id.getPonto_de_referencia());
    }
    @Transactional
    public void excluirCliente(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
