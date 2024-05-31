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

import com.jogos.ecommerce.domain.dto.input.INPUT_FuncionarioDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_FuncionarioDTO;
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
    public List<OUTPUT_FuncionarioDTO> listar(){
     
        return funcionarioService.ListarFuncionarios();
     }

     @GetMapping("/{funcionarioId}")
     public ResponseEntity<OUTPUT_FuncionarioDTO> buscarPorId(@PathVariable Long funcionarioId){
        Optional<Funcionario> pesquisaPeloFuncionario=funcionarioRepository.findById(funcionarioId);

        if(pesquisaPeloFuncionario.isPresent()){
            var funcionario=pesquisaPeloFuncionario.get();
            var funcionarioDTO=new OUTPUT_FuncionarioDTO(funcionarioId, funcionario.getNome(),funcionario.getTelefone(),funcionario.getDt_nasc(),funcionario.getCpf(), funcionario.getSalario(),funcionario.getCep(),funcionario.getPais(),funcionario.getEstado(),funcionario.getCidade(),funcionario.getBairro(), funcionario.getLogradouro(), funcionario.getNumero(), funcionario.getPonto_de_referencia(), funcionario.getCargo());
            return ResponseEntity.ok(funcionarioDTO); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_FuncionarioDTO cadrastrar(@Valid @RequestBody  INPUT_FuncionarioDTO funcionarioDTO){
        // return funcionarioRepository.save(funcionario);
        return funcionarioService.salvarFuncionario(funcionarioDTO);
     }

     @PutMapping("/{funcionarioId}")
     public ResponseEntity<OUTPUT_FuncionarioDTO> atualizar(@PathVariable Long funcionarioId,@Valid @RequestBody INPUT_FuncionarioDTO funcionarioDTO){
         if(funcionarioRepository.existsById(funcionarioId)==false){
             return ResponseEntity.notFound().build();
         }
         OUTPUT_FuncionarioDTO funcionario_editado=funcionarioService.editarFuncionario(funcionarioDTO);
         return ResponseEntity.ok(funcionario_editado);

        
     }

     @DeleteMapping("/{funcionarioId}")
     public ResponseEntity<Void> deletarfuncionario(@PathVariable Long funcionarioId){
        if(funcionarioRepository.existsById(funcionarioId)==false){
            return ResponseEntity.notFound().build();
        }

        funcionarioService.excluirFuncionario(funcionarioId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
