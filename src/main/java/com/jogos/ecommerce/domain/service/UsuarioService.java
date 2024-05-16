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
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    

    public List<Usuario> ListarUsuario(Usuario usuario){
        return usuarioRepository.findAll();
    }
    @Transactional
    public Usuario salvarUsuario(Usuario usuario){
       return  usuarioRepository.save(usuario);
    }
    @Transactional
    public Usuario editarUsuario(Usuario usuario){
       return  usuarioRepository.save(usuario);
    }
    @Transactional
    public void excluirUsuario(Long usuarioId){
        usuarioRepository.deleteById(usuarioId);
    }
}
