package com.loja.backendlojanelio.resources.dto;

import com.loja.backendlojanelio.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

    private Integer id;
    private String nome;

    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }
}
