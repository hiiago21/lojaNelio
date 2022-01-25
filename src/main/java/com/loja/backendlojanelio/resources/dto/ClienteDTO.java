package com.loja.backendlojanelio.resources.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.domain.Endereco;
import com.loja.backendlojanelio.domain.Pedido;
import com.loja.backendlojanelio.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    @JsonInclude(NON_NULL)
    private TipoCliente tipoCliente;
    @JsonInclude(NON_EMPTY)
    private List<Endereco> enderecos = new ArrayList<>();
    @JsonInclude(NON_EMPTY)
    private Set<String> telefones = new HashSet<>();
    @JsonInclude(NON_EMPTY)
    private List<Pedido> pedidos = new ArrayList<>();


    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.cpfOuCnpj = obj.getCpfOuCnpj();
        this.tipoCliente = TipoCliente.toEnum(obj.getTipoCliente());
        this.enderecos = obj.getEnderecos();
        this.telefones = obj.getTelefones();
        this.pedidos = obj.getPedidos();
    }

    public static ClienteDTO toDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .cpfOuCnpj(cliente.getCpfOuCnpj())
                .tipoCliente(TipoCliente.toEnum(cliente.getTipoCliente()))
                .build();
    }
}
