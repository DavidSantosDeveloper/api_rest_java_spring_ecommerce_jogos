package com.jogos.ecommerce.domain.model;
import com.jogos.ecommerce.domain.dto.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Produto {   
    @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="cod_produto")
    private Long id;
    
    @NotBlank
    private String nome;
    @Positive
    private double preco;
    private String foto_url;
    private  String descricao;
    private String categoria; 

    @OneToMany(mappedBy = "produto")
    private List<ItemCarrinho> itemCarrinhos;
    @OneToMany(mappedBy = "produto")
    private List<ItemVenda> itemVendas;


    public Produto(ProdutoDTO produto_dto){
        this.nome=produto_dto.nome();
        this.preco=produto_dto.preco();
        this.foto_url=produto_dto.foto_url();
        this.descricao=produto_dto.descricao();
        this.categoria=produto_dto.categoria();
    }
    public Produto(Long produtoId,ProdutoDTO produto_dto){
        this.id=produtoId;
        this.nome=produto_dto.nome();
        this.preco=produto_dto.preco();
        this.foto_url=produto_dto.foto_url();
        this.descricao=produto_dto.descricao();
        this.categoria=produto_dto.categoria();
    }   

}
