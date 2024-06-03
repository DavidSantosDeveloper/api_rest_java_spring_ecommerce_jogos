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

import com.jogos.ecommerce.domain.dto.input.INPUT_VendaDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_VendaDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;
import com.jogos.ecommerce.domain.service.*;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

@AllArgsConstructor        // gera um construtor automaticamente
@RestController
@RequestMapping("/vendas")

public class VendaController {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private VendaService vendaService;


    @GetMapping("")
    public List<OUTPUT_VendaDTO> listar(){
     
        return vendaService.ListarVendas();
     }

     @GetMapping("/{vendaId}")
     public ResponseEntity<OUTPUT_VendaDTO> buscarPorId(@PathVariable Long vendaId){
        Optional<Venda> pesquisaPeloVenda=vendaRepository.findById(vendaId);

        if(pesquisaPeloVenda.isPresent()){
            Venda venda=pesquisaPeloVenda.get();
            OUTPUT_VendaDTO vendaDTO=new OUTPUT_VendaDTO(venda.getId(),venda.getDt_venda(),venda.getValor_total(),venda.getUsuario());
            return ResponseEntity.ok(vendaDTO); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_VendaDTO cadrastrar(@Valid @RequestBody  INPUT_VendaDTO vendaDTO){
        // return vendaRepository.save(venda);
        return vendaService.salvarVenda(vendaDTO);
     }

     @PutMapping("/{vendaId}")
     public ResponseEntity<OUTPUT_VendaDTO>atualizar(@PathVariable Long vendaId,@Valid @RequestBody INPUT_VendaDTO vendaDTO){
         if(vendaRepository.existsById(vendaId)==false){
             return ResponseEntity.notFound().build();
         }
        
        OUTPUT_VendaDTO venda_editada=vendaService.editarVenda(vendaDTO);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o venda
         return ResponseEntity.ok(venda_editada);
     }

     @DeleteMapping("/{vendaId}")
     public ResponseEntity<Void> deletarVenda(@PathVariable Long vendaId){
        if(vendaRepository.existsById(vendaId)==false){
            return ResponseEntity.notFound().build();
        }

        vendaService.excluirVenda(vendaId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
