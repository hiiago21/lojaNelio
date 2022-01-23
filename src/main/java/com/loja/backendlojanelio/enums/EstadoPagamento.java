package com.loja.backendlojanelio.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static java.util.Objects.isNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer cod;
    private String descricao;

    public static EstadoPagamento toEnum(Integer cod){
        if (isNull(cod)){
            return null;
        }

        return Arrays.stream(EstadoPagamento.values()).filter(estado -> estado.getCod().equals(cod)).findFirst().orElseThrow(()->{
            throw new IllegalArgumentException("Id de EstadoPagamento inválido id: "+cod);
        });
    }

}
