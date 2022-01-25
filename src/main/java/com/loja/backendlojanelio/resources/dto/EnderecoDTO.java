package com.loja.backendlojanelio.resources.dto;

import com.loja.backendlojanelio.domain.Cidade;
import com.loja.backendlojanelio.domain.Cliente;
import com.loja.backendlojanelio.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {

    private Integer id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Integer cidadeId;

    public Endereco toEntitty(Cliente cliente) {
        return Endereco.builder()
                .bairro(this.getBairro())
                .logradouro(this.getLogradouro())
                .numero(this.getNumero())
                .complemento(this.getComplemento())
                .bairro(this.getBairro())
                .cep(this.getCep())
                .cidade(Cidade.builder().id(this.getCidadeId()).build())
                .cliente(cliente)
                .build();
    }
}
