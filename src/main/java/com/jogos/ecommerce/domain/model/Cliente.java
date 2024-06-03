package com.jogos.ecommerce.domain.model;

import java.sql.Date;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    
     @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="cod_cliente")
    private Long id;
    
    @NotBlank
    private String nome;
    @Size(max=20)
    private String telefone;
    @NotBlank
    private  Date dt_nasc;
    @NotBlank
    private String cpf; 

    private String cep;
    private String pais; 
    private String estado;
    private String cidade; 
    private String  bairro;
    private String logradouro;
    private String numero;
    private String ponto_de_referencia;

    @OneToOne(mappedBy = "cliente")
    private Usuario usuario;


}
