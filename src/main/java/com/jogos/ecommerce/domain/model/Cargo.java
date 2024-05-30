package com.jogos.ecommerce.domain.model;


import java.util.List;

import com.jogos.ecommerce.domain.dto.CargoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
public class Cargo {
     @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="cod_cargo")
    private Long id;
    @NotBlank
    private  String nome;
    @NotBlank
    private  String descricao;

    @OneToMany(mappedBy = "cargo")
   
    private List<Funcionario> funcionarios;

    public Cargo(CargoDTO cargoDTO){
        this.nome=cargoDTO.nome();
        this.descricao=cargoDTO.descricao();
    }
    public Cargo(Long id,CargoDTO cargoDTO){
        this.id=id;
        this.nome=cargoDTO.nome();
        this.descricao=cargoDTO.descricao();
    }
}
