package br.com.alura.AluraFake.core.exception;

import java.util.List;

public class InvalidTaskException extends RuntimeException {

    private List<ErrorItem> errors;

    public InvalidTaskException(List<ErrorItem> errors) {
        this.errors = errors;
    }

    public List<ErrorItem> getErrors(){
        return this.errors;
    }
}
