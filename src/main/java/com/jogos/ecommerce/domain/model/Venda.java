package com.jogos.ecommerce.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)

public class Venda {
    
    @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="cod_venda")
    private Long id;
    @NotBlank
    private  Date dt_venda;
    @NotBlank
    private double valor_total; 
    
    @ManyToOne
    @JoinColumn(name="cod_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itemVendas;
    
}
