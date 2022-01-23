package com.loja.backendlojanelio.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static java.util.Objects.isNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private Integer cod;
    private String descricao;

    public static TipoCliente toEnum(Integer cod){
        if (isNull(cod)){
            return null;
        }

        return Arrays.stream(TipoCliente.values()).filter(tipo -> tipo.getCod().equals(cod)).findFirst().orElseThrow(()->{
            throw new IllegalArgumentException("Id de TipoCliente inválido id: "+cod);
        });
    }
}
