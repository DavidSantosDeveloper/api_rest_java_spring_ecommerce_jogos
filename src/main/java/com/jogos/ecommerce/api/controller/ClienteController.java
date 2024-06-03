package com.jogos.ecommerce.api.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.jogos.ecommerce.domain.dto.input.INPUT_ClienteDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ClienteDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;
import com.jogos.ecommerce.domain.service.*;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

@AllArgsConstructor        // gera um construtor automaticamente
@RestController
@RequestMapping("/clientes")

public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;


    @GetMapping("")
    public List<OUTPUT_ClienteDTO> listar(){
     
        return clienteService.ListarClientes();
     }

     @GetMapping("/{clienteId}")
     public ResponseEntity<OUTPUT_ClienteDTO> buscarPorId(@PathVariable Long clienteId){
        Optional<OUTPUT_ClienteDTO> pesquisaPeloCliente=Optional.ofNullable(clienteService.findById(clienteId));

        if(pesquisaPeloCliente.isPresent()){
            return ResponseEntity.ok(pesquisaPeloCliente.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_ClienteDTO cadrastrar(@Valid @RequestBody  INPUT_ClienteDTO clienteDTO){
        return clienteService.salvarCliente(clienteDTO);
     }

     @PutMapping("/{clienteId}")
     public ResponseEntity<OUTPUT_ClienteDTO>atualizar(@PathVariable Long clienteId,@Valid @RequestBody INPUT_ClienteDTO clienteDTO){
        if(clienteRepository.existsById(clienteId)==false){
            return ResponseEntity.notFound().build();
        }
       
        OUTPUT_ClienteDTO cliente_editado=clienteService.editarCliente(clienteDTO);
        return ResponseEntity.ok(cliente_editado);
        
     }

     @DeleteMapping("/{clienteId}")
     public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId){
        if(clienteRepository.existsById(clienteId)==false){
            return ResponseEntity.notFound().build();
        }
        clienteService.excluirCliente(clienteId);
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
