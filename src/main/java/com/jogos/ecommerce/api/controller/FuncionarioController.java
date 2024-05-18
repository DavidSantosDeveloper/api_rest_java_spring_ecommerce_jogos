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
@RequestMapping("/funcionarios")

public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FuncionarioService funcionarioService;


    @GetMapping("")
    public List<Funcionario> listar(){
     
        return funcionarioRepository.findAll();
     }

     @GetMapping("/{funcionarioId}")
     public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long funcionarioId){
        Optional<Funcionario> pesquisaPeloFuncionario=funcionarioRepository.findById(funcionarioId);

        if(pesquisaPeloFuncionario.isPresent()){
            return ResponseEntity.ok(pesquisaPeloFuncionario.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public Funcionario cadrastrar(@Valid @RequestBody  Funcionario funcionario){
        // return funcionarioRepository.save(funcionario);
        return funcionarioService.salvarFuncionario(funcionario);
     }

     @PutMapping("/{funcionarioId}")
     public ResponseEntity<Funcionario>atualizar(@PathVariable Long funcionarioId,@Valid @RequestBody Funcionario funcionario){
         if(funcionarioRepository.existsById(funcionarioId)==false){
             return ResponseEntity.notFound().build();
         }
        //  associa o id passado via url ao objeto java criado(que foi criado via dados do corpo body)
         funcionario.setId(funcionarioId);
        //  metodo save verifica existe um funcionario com id informado:Se sim,atualiza os dados.Senão cria um novo registro na tabela funcionario 
        //  funcionarioRepository.save(funcionario);
        funcionarioService.salvarFuncionario(funcionario);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o funcionario
         return ResponseEntity.ok(funcionario);
     }

     @DeleteMapping("/{funcionarioId}")
     public ResponseEntity<Void> deletarfuncionario(@PathVariable Long funcionarioId){
        if(funcionarioRepository.existsById(funcionarioId)==false){
            return ResponseEntity.notFound().build();
        }

        funcionarioRepository.deleteById(funcionarioId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
