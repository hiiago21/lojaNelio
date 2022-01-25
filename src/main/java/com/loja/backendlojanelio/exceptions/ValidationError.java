package com.loja.backendlojanelio.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String mensagem, Long timeStamp) {
        super(status, mensagem, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldMessage> errors) {
        this.errors = errors;
    }
}
