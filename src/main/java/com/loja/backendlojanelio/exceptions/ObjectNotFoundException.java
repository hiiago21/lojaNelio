package com.loja.backendlojanelio.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ObjectNotFoundException extends RuntimeException{

    private String mensagem;

}
