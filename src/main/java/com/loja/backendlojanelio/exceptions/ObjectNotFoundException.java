package com.loja.backendlojanelio.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    private final String mensagem;

    public ObjectNotFoundException(String mensagem) {
        this.mensagem = mensagem;
    }

    public ObjectNotFoundException(String message, Throwable cause, String mensagem) {
        super(message, cause);
        this.mensagem = mensagem;
    }
}
