package br.com.alura.AluraFake.core.exception;

import java.util.List;

public class InvalidCourseException extends RuntimeException {
    private final List<ErrorItem> errors;

    public InvalidCourseException(List<ErrorItem> errors) {
        this.errors = errors;
    }

    public InvalidCourseException(String message) {
        super(message);
        errors = null;
    }


    public List<ErrorItem> getErrors(){
        return this.errors;
    }
}
