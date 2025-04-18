package com.jogos.ecommerce.domain.model;

import java.sql.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
   
    @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="cod_usuario")
    private Long id;

     @NotBlank
    private String email;
    @NotBlank
    private String senha;
   
    private String foto_perfil_url;

    @OneToOne
    @JoinColumn(name="cod_cliente")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name="cod_funcionario")
    private Funcionario funcionario;

     @OneToOne(mappedBy = "usuario")
     private Carrinho carrinho;

     @OneToMany(mappedBy = "usuario")
     private List<Venda> vendas;

     public Usuario( String email, String senha, String foto_perfil_url, Cliente cliente,Funcionario funcionario) {
        this.email=email;
        this.senha=senha;
        this.foto_perfil_url=foto_perfil_url;
        this.cliente=cliente;
        this.funcionario=funcionario;
     }


}
