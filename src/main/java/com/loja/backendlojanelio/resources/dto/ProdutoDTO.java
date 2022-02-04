package com.loja.backendlojanelio.resources.dto;

import com.loja.backendlojanelio.domain.Produto;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProdutoDTO implements Serializable {

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}
