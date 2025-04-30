package br.com.alura.AluraFake.core.exception;

public class CourseNotExistsException extends RuntimeException {
    public CourseNotExistsException(String message) {
        super(message);
    }
}
