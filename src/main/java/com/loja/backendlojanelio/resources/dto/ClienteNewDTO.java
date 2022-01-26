package com.loja.backendlojanelio.resources.dto;

import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.services.validators.ClienteInsert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ClienteInsert
public class ClienteNewDTO implements Serializable {

    @NotEmpty(message="Preenchimento obrigatório")
    @Size(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;
    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    private String email;
    @NotEmpty(message="Preenchimento obrigatório")
    private String cpfOuCnpj;
    private Integer tipoCliente;
    private final List<EnderecoDTO> enderecos = new ArrayList<>();
    @NotEmpty(message="Preenchimento obrigatório")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    public Cliente toClienteEntity() {
        Cliente cliente = Cliente.builder()
                .email(this.getEmail())
                .tipoCliente(this.getTipoCliente())
                .nome(this.getNome())
                .cpfOuCnpj(this.cpfOuCnpj)
                .build();

        cliente.getTelefones().addAll(Arrays.asList(telefone1, telefone2, telefone3));
        cliente.getEnderecos().addAll(enderecos.stream().map(endereco -> endereco.toEntitty(cliente)).collect(Collectors.toList()));
        return cliente;

    }
}
