package com.jogos.ecommerce.domain.model;

import java.sql.Date;

import java.lang.String;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)

public class Funcionario {
     @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="cod_funcionario")
    private Long id;
    
    @NotBlank
    private String nome;
    @Size(max=20)
    private String telefone;
    @NotBlank
    private  Date dt_nasc;
    @NotBlank
    private String cpf; 

    
    @NotBlank
    private String cep;
    @NotBlank
    private String pais; 
    @NotBlank
    private String Estado;
    @NotBlank
    private String cidade; 
    @NotBlank
    private String  bairro;
    @NotBlank
    private String logradouro; 
    @NotBlank
    private String numero; 
    @NotBlank
    private String ponto_de_referencia;

    @ManyToOne
    @JoinColumn(name="cargo")
    private Cargo cargo;

    @OneToOne(mappedBy = "funcionario")
    @JoinColumn(name="cod_usuario")
    private Usuario usuario;
}
