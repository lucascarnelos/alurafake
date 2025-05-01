package br.com.alura.AluraFake.core.usecase.task.impl;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.exception.InvalidTaskException;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.rules.ValidationRule;
import br.com.alura.AluraFake.core.rules.task.*;
import br.com.alura.AluraFake.core.usecase.task.TaskWithChoiceValidateUseCase;

import java.util.ArrayList;
import java.util.List;

public class TaskWithChoiceValidateUseCaseImpl implements TaskWithChoiceValidateUseCase {

    @Override
    public void execute(Task task) {
        List<ErrorItem> errors = new ArrayList<>();
        for(ValidationRule<Task> validationRule : List.of(
                new MaximoAlternativasTaskRule(),
                new AlternativaTrueUnicaRule(),
                new MaxCaracterRule(),
                new AlternativasIguaisRule(),
                new AlternativaIgualEnunciadoTaskRule())
        ){
            validationRule.execute(errors, task);
        }
        if(!errors.isEmpty())
            throw new InvalidTaskException(errors);

    }

}
