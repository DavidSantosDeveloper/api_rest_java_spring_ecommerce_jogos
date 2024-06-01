package com.jogos.ecommerce.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jogos.ecommerce.domain.dto.input.INPUT_UsuarioDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_UsuarioDTO;
import com.jogos.ecommerce.domain.exception.*;
import com.jogos.ecommerce.domain.model.*;
import com.jogos.ecommerce.domain.repository.*;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    
      public OUTPUT_UsuarioDTO findById(Long itemVendaId){
        Optional<Usuario> buscaPeloUsuario=usuarioRepository.findById(itemVendaId);
        if (buscaPeloUsuario.isEmpty()) {
            return null;
        }
        Usuario usuario=buscaPeloUsuario.get();
        return new OUTPUT_UsuarioDTO(usuario.getId(),usuario.getEmail(), usuario.getSenha(),usuario.getFoto_perfil_url(), usuario.getCliente(), usuario.getFuncionario());

    }
    public List<OUTPUT_UsuarioDTO> ListarUsuario(){
        return usuarioRepository.findAllUsuarios();
    }
    @Transactional
    public OUTPUT_UsuarioDTO salvarUsuario(INPUT_UsuarioDTO usuarioDTO){
       
       Usuario usuario_sem_id=new Usuario(usuarioDTO.email(),usuarioDTO.senha(),usuarioDTO.foto_perfil_url(),usuarioDTO.cliente(),usuarioDTO.funcionario());
       Usuario usuario_com_id=usuarioRepository.save(usuario_sem_id);
        return new OUTPUT_UsuarioDTO(usuario_com_id.getId(), usuario_com_id.getEmail(), usuario_com_id.getSenha(), usuario_com_id.getFoto_perfil_url(), usuario_com_id.getCliente(), usuario_com_id.getFuncionario());
    }
    @Transactional
    public OUTPUT_UsuarioDTO editarUsuario(INPUT_UsuarioDTO usuarioDTO){
       
        Usuario usuario_sem_id=new Usuario(usuarioDTO.email(),usuarioDTO.senha(),usuarioDTO.foto_perfil_url(),usuarioDTO.cliente(),usuarioDTO.funcionario());
        Usuario usuario_com_id=usuarioRepository.save(usuario_sem_id);
         return new OUTPUT_UsuarioDTO(usuario_com_id.getId(), usuario_com_id.getEmail(), usuario_com_id.getSenha(), usuario_com_id.getFoto_perfil_url(), usuario_com_id.getCliente(), usuario_com_id.getFuncionario());
     }
    @Transactional
    public void excluirUsuario(Long usuarioId){
        usuarioRepository.deleteById(usuarioId);
    }
}
