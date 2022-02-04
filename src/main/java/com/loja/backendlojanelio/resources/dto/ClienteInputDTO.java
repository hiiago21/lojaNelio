package com.loja.backendlojanelio.resources.dto;

import com.loja.backendlojanelio.services.validators.ClienteUpdate;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ClienteUpdate
public class ClienteInputDTO {

    @NotEmpty(message="Preenchimento obrigatório")
    @Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;
    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;

}
