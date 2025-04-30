package br.com.alura.AluraFake.core.exception;
public class ErrorItem {

    private final String field;
    private final String message;

    public ErrorItem(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
