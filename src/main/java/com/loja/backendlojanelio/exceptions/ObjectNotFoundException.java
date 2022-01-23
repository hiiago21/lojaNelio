package com.loja.backendlojanelio.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ObjectNotFoundException extends RuntimeException{

    private String mensagem;

}
