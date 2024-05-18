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
    public List<Cliente> listar(){
     
        return clienteRepository.findAll();
     }

     @GetMapping("/{clienteId}")
     public ResponseEntity<Cliente> buscarPorId(@PathVariable Long clienteId){
        Optional<Cliente> pesquisaPeloCliente=clienteRepository.findById(clienteId);

        if(pesquisaPeloCliente.isPresent()){
            return ResponseEntity.ok(pesquisaPeloCliente.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public Cliente cadrastrar(@Valid @RequestBody  Cliente cliente){
        // return clienteRepository.save(cliente);
        return clienteService.salvarCliente(cliente);
     }

     @PutMapping("/{clienteId}")
     public ResponseEntity<Cliente>atualizar(@PathVariable Long clienteId,@Valid @RequestBody Cliente cliente){
         if(clienteRepository.existsById(clienteId)==false){
             return ResponseEntity.notFound().build();
         }
        //  associa o id passado via url ao objeto java criado(que foi criado via dados do corpo body)
         cliente.setId(clienteId);
        //  metodo save verifica existe um cliente com id informado:Se sim,atualiza os dados.Senão cria um novo registro na tabela cliente 
        //  clienteRepository.save(cliente);
        clienteService.salvarCliente(cliente);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o cliente
         return ResponseEntity.ok(cliente);
     }

     @DeleteMapping("/{clienteId}")
     public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId){
        if(clienteRepository.existsById(clienteId)==false){
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(clienteId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
