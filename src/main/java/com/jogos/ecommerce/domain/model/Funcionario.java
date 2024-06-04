package com.jogos.ecommerce.domain.model;

import java.sql.Date;

import com.jogos.ecommerce.domain.dto.input.INPUT_FuncionarioDTO;
import com.jogos.ecommerce.domain.dto.output.OUTPUT_FuncionarioDTO;

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
    private double salario;
    
    @NotBlank
    private String cep;
    @NotBlank
    private String pais; 
    @NotBlank
    private String estado;
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


    public Funcionario(OUTPUT_FuncionarioDTO funcionarioDTO){ 
        this.id=funcionarioDTO.id();  
        this.nome=funcionarioDTO.nome();
        this.telefone=funcionarioDTO.telefone();
        this.dt_nasc=funcionarioDTO.dt_nasc();
        this.cpf=funcionarioDTO.cpf();
        this.salario=funcionarioDTO.salario();
        this.cep=funcionarioDTO.cep();
        this.estado=funcionarioDTO.estado();
        this.pais=funcionarioDTO.pais();
        this.cidade=funcionarioDTO.cidade();
        this.bairro=funcionarioDTO.bairro();
        this.logradouro=funcionarioDTO.logradouro();
        this.numero=funcionarioDTO.numero();
        this.ponto_de_referencia=funcionarioDTO.ponto_de_referencia();
        this.cargo=funcionarioDTO.cargo();
    }
    public Funcionario(INPUT_FuncionarioDTO funcionarioDTO){  
        this.nome=funcionarioDTO.nome();
        this.telefone=funcionarioDTO.telefone();
        this.dt_nasc=funcionarioDTO.dt_nasc();
        this.cpf=funcionarioDTO.cpf();
        this.salario=funcionarioDTO.salario();
        this.cep=funcionarioDTO.cep();
        this.estado=funcionarioDTO.estado();
        this.pais=funcionarioDTO.pais();
        this.cidade=funcionarioDTO.cidade();
        this.bairro=funcionarioDTO.bairro();
        this.logradouro=funcionarioDTO.logradouro();
        this.numero=funcionarioDTO.numero();
        this.ponto_de_referencia=funcionarioDTO.ponto_de_referencia();
        this.cargo=funcionarioDTO.cargo();
    }
    

}
