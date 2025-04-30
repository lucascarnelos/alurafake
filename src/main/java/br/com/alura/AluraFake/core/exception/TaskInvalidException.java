package br.com.alura.AluraFake.core.exception;

import java.util.List;

public class TaskInvalidException extends RuntimeException {

    private List<ErrorItem> errors;

    public TaskInvalidException(List<ErrorItem> errors) {
        this.errors = errors;
    }

    public List<ErrorItem> getErrors(){
        return this.errors;
    }
}
