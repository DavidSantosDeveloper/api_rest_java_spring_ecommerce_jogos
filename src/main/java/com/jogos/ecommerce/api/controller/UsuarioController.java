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

import com.jogos.ecommerce.domain.dto.input.INPUT_UsuarioDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_ItemVenda_DTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_UsuarioDTO;
import com.jogos.ecommerce.domain.exception.RegraDeNegocioException;
import com.jogos.ecommerce.domain.model.ItemVenda;
import com.jogos.ecommerce.domain.model.Usuario;
import com.jogos.ecommerce.domain.repository.UsuarioRepository;
import com.jogos.ecommerce.domain.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor        // gera um construtor automaticamente
@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("")
    public List< OUTPUT_UsuarioDTO> listar(){
        return usuarioService.ListarUsuario();
     }

     @GetMapping("/{usuarioId}")
     public ResponseEntity<OUTPUT_UsuarioDTO> buscarPorId(@PathVariable Long usuarioId){
        Optional<OUTPUT_UsuarioDTO> pesquisaPeloUsuario=Optional.ofNullable(usuarioService.findById(usuarioId));

        if(pesquisaPeloUsuario.isPresent()){
            return ResponseEntity.ok(pesquisaPeloUsuario.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public OUTPUT_UsuarioDTO cadrastrar(@Valid @RequestBody INPUT_UsuarioDTO usuarioDTO){
        return usuarioService.salvarUsuario(usuarioDTO);
     }

     @PutMapping("/{usuarioId}")
     public ResponseEntity<OUTPUT_UsuarioDTO >atualizar(@PathVariable Long usuarioId,@Valid @RequestBody INPUT_UsuarioDTO usuarioDTO){
         if(usuarioRepository.existsById(usuarioId)==false){
             return ResponseEntity.notFound().build();
         }
        OUTPUT_UsuarioDTO usuario_editado=usuarioService.editarUsuario(usuarioDTO);
         return ResponseEntity.ok(usuario_editado);
     }

     @DeleteMapping("/{usuarioId}")
     public ResponseEntity<Void> deletarUsuario(@PathVariable Long usuarioId){
        if(usuarioRepository.existsById(usuarioId)==false){
            return ResponseEntity.notFound().build();
        }

        usuarioService.excluirUsuario(usuarioId);
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
