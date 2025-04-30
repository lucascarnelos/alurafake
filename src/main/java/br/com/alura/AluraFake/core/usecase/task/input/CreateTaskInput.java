package br.com.alura.AluraFake.core.usecase.task.input;

import java.util.List;

public record CreateTaskInput(
        Long courseId,
        String statement,
        Integer order,
        List<CreateTaskOption> options
) {

    public record CreateTaskOption(
            String option,
            Boolean isCorrect
    ){
    }

}
