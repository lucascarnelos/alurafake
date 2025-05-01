package br.com.alura.AluraFake.core.rules;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;

import java.util.List;

public interface ValidationRule<T> {
    void execute(List<ErrorItem> errorList, T t);
}
