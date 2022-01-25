package com.loja.backendlojanelio.resources.dto;

import com.loja.backendlojanelio.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteNewDTO implements Serializable {

    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente;
    private final List<EnderecoDTO> enderecos = new ArrayList<>();
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
