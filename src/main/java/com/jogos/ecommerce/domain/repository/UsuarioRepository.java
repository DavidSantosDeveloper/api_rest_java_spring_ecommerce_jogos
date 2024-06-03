package com.jogos.ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_UsuarioDTO;
import com.jogos.ecommerce.domain.model.*;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findById(long id);

     @Query("SELECT new com.jogos.ecommerce.domain.dto.output.OUTPUT_UsuarioDTO(u.id,u.email,u.senha,u.foto_perfil_url,u.cliente,u.funcionario) FROM Usuario u")
    List<OUTPUT_UsuarioDTO> findAllUsuarios();
}


