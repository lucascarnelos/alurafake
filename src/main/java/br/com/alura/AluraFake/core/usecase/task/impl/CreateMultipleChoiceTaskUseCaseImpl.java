package br.com.alura.AluraFake.core.usecase.task.impl;

import br.com.alura.AluraFake.core.exception.CourseNotExistsException;
import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.exception.InvalidTaskException;
import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.usecase.task.CreateMultipleChoiceTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.OrderingTasksUseCase;
import br.com.alura.AluraFake.core.usecase.task.ValidaTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.input.CreateTaskInput;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateMultipleChoiceTaskUseCaseImpl implements CreateMultipleChoiceTaskUseCase {

    private final TaskPersistenceGateway taskPersistenceGateway;
    private final CoursePersistenceGateway coursePersistenceGateway;
    private final ValidaTaskUseCase validaTaskUseCase;
    private final OrderingTasksUseCase orderingTasksUseCase;

    public CreateMultipleChoiceTaskUseCaseImpl(TaskPersistenceGateway taskPersistenceGateway, CoursePersistenceGateway coursePersistenceGateway, ValidaTaskUseCase validaTaskUseCase, OrderingTasksUseCase orderingTasksUseCase) {
        this.taskPersistenceGateway = taskPersistenceGateway;
        this.coursePersistenceGateway = coursePersistenceGateway;
        this.validaTaskUseCase = validaTaskUseCase;
        this.orderingTasksUseCase = orderingTasksUseCase;
    }

    @Override
    public Task execute(CreateTaskInput input) {
        var course = coursePersistenceGateway.findById(input.courseId());
        if(course == null)
            throw new CourseNotExistsException("");


        var task = Task.Create(
                Type.MULTIPLE_CHOICE,
                course,
                input.statement(),
                input.order(),
                null
        );

        var taskOptions = input.options().stream().map(tskOpt -> TaskOption.Create(task, tskOpt.option(), tskOpt.isCorrect())).toList();
        task.setOptions(taskOptions);
        valid(task);

        validaTaskUseCase.execute(task);
        orderingTasksUseCase.execute(task);
        return taskPersistenceGateway.save(task);
    }


    private void valid(Task task){
        var taskOptions = task.getOptions();
        boolean maximoAlternativasTask = false;
        boolean maxCaracterError = false;
        boolean alternativasIguaisError = false;
        boolean alternativaIgualEnunciadoError = false;


        if(taskOptions == null || !(taskOptions.size() >= 3 && taskOptions.size() <= 5))
            maximoAlternativasTask = true;

        Set<String> uniqueOptions = new HashSet<>();
        int correctOptions = 0;
        int incorrectOptions = 0;

        if(taskOptions != null && !taskOptions.isEmpty()){
            for(TaskOption taskOption : taskOptions){
                if(taskOption.getCorrect())
                    correctOptions++;
                if(!taskOption.getCorrect())
                    incorrectOptions++;
                if(taskOption.getOption() == null || !(taskOption.getOption().length() >= 4 && taskOption.getOption().length() <= 80))
                    maxCaracterError = true;
                if(!uniqueOptions.add(taskOption.getOption()))
                    alternativasIguaisError = true;
                if(taskOption.getOption().equals(task.getStatement()))
                    alternativaIgualEnunciadoError = true;
            }
        }

        List<ErrorItem> errors = new ArrayList<>();

        if(correctOptions < 2)
            errors.add(new ErrorItem("isCorrect","A atividade deve ter 2 ou mais alternativas corretas."));
        if(incorrectOptions < 1)
            errors.add(new ErrorItem("isCorrect","A atividade deve ter ao menos uma alternativa  incorreta."));

        if(maximoAlternativasTask) errors.add(new ErrorItem("options","A atividade deve ter no minimo 3 e no máximo 5 alternativas."));
        if(maxCaracterError) errors.add(new ErrorItem("option","As alternativas devem ter no mínimo 4 e no máximo 80 caracteres."));
        if(alternativasIguaisError) errors.add(new ErrorItem("option","As alternativas não podem ser iguais entre si."));
        if(alternativaIgualEnunciadoError) errors.add(new ErrorItem("option","As alternativas não podem ser iguais ao enunciado da atividade."));

        if(!errors.isEmpty())
            throw new InvalidTaskException(errors);
    }

}
