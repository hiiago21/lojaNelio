package com.loja.backendlojanelio.exceptions;

public class DataIntegrityException extends RuntimeException{

    private final String mensagem;

    public DataIntegrityException(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataIntegrityException(String message, Throwable cause, String mensagem) {
        super(message, cause);
        this.mensagem = mensagem;
    }
}
