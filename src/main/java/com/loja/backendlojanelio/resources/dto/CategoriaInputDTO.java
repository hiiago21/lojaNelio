package com.loja.backendlojanelio.resources.dto;

import com.loja.backendlojanelio.domain.Categoria;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaInputDTO implements Serializable {

    @NotNull
    private Integer id;
    @NotEmpty(message="Preenchimento obrigatório")
    @Size(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public Categoria toCategoriaEntity(){
        return Categoria.builder()
                .id(this.id)
                .nome(this.nome)
                .build();
    }
}
