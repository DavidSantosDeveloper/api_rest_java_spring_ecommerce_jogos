package com.jogos.ecommerce.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Carrinho implements Serializable{
    @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="cod_carrinho")
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "cod_usuario")
    private Usuario usuario;          

    private double total;

    @OneToMany(mappedBy = "carrinho")
    private List<ItemCarrinho> itemCarrinhos;

  
}
