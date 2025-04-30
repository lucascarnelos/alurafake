package br.com.alura.AluraFake.core.usecase.course.rules;

import br.com.alura.AluraFake.core.model.task.Task;

public interface ValidationRule<T> {
    void execute(T t);
}
