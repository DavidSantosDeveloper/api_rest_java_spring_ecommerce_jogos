package com.jogos.ecommerce.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Date;

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
public class ItemVenda {
    @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="cod_item_venda")
    private Long id;
    private Long quantidade;
    private double preco_unitario;

    @ManyToOne
    @JoinColumn(name = "cod_venda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name="cod_produto")
    private Produto produto;

    public ItemVenda(Long quantidade, double preco_unitario, Venda venda, Produto produto) {
        this.quantidade=quantidade;
        this.preco_unitario=preco_unitario;
        this.venda=venda;
        this.produto=produto;
    }

}
