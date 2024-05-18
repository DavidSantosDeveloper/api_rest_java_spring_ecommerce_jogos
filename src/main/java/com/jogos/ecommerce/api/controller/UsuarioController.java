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
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("")
    public List<Usuario> listar(){
     
        return usuarioRepository.findAll();
     }

     @GetMapping("/{usuarioId}")
     public ResponseEntity<Usuario> buscarPorId(@PathVariable Long usuarioId){
        Optional<Usuario> pesquisaPeloUsuario=usuarioRepository.findById(usuarioId);

        if(pesquisaPeloUsuario.isPresent()){
            return ResponseEntity.ok(pesquisaPeloUsuario.get()); 
        }
        else{
            return ResponseEntity.notFound().build();
        }
     }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
     public Usuario cadrastrar(@Valid @RequestBody  Usuario usuario){
        // return usuarioRepository.save(usuario);
        return usuarioService.salvarUsuario(usuario);
     }

     @PutMapping("/{usuarioId}")
     public ResponseEntity<Usuario>atualizar(@PathVariable Long usuarioId,@Valid @RequestBody Usuario usuario){
         if(usuarioRepository.existsById(usuarioId)==false){
             return ResponseEntity.notFound().build();
         }
        //  associa o id passado via url ao objeto java criado(que foi criado via dados do corpo body)
         usuario.setId(usuarioId);
        //  metodo save verifica existe um usuario com id informado:Se sim,atualiza os dados.Senão cria um novo registro na tabela usuario 
        //  usuarioRepository.save(usuario);
        usuarioService.salvarUsuario(usuario);
        //  retorna operacao PUT feita com sucesso! e envia uma resposta com o json que representa o usuario
         return ResponseEntity.ok(usuario);
     }

     @DeleteMapping("/{usuarioId}")
     public ResponseEntity<Void> deletarUsuario(@PathVariable Long usuarioId){
        if(usuarioRepository.existsById(usuarioId)==false){
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.deleteById(usuarioId);
        // Executou com suceeso e resposta sem body (Ideal para metodo http delete)
        return ResponseEntity.noContent().build();
     }

     @ExceptionHandler(RegraDeNegocioException.class)
     public ResponseEntity<String> capturarExcecao(RegraDeNegocioException erro){
        return ResponseEntity.badRequest().body(erro.getMessage());
     }
}
