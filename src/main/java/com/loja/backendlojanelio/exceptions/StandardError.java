package com.loja.backendlojanelio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class StandardError implements Serializable {

    private Integer status;
    private String mensagem;
    private Long timeStamp;
}
