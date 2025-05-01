package br.com.alura.AluraFake.core.usecase.task.impl;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.exception.InvalidTaskException;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.usecase.task.GenericTaskValidateUseCase;

import java.util.ArrayList;
import java.util.List;

public class GerenicGenericTaskValidateUseCaseImpl implements GenericTaskValidateUseCase {

    private final TaskPersistenceGateway taskPersistenceGateway;

    public GerenicGenericTaskValidateUseCaseImpl(TaskPersistenceGateway taskPersistenceGateway) {
        this.taskPersistenceGateway = taskPersistenceGateway;
    }

    @Override
    public void execute(Task task) {
        List<ErrorItem> errors = new ArrayList<>();
        if(task.getOrder() <= 0){
            errors.add(new ErrorItem("order","A ordem deve ser um número inteiro positivo"));
        }
        if(!Status.BUILDING.equals(task.getCourse().getStatus())){
            errors.add(new ErrorItem("courseId","Um curso só pode receber atividades se seu status for BULDING"));
        }
        if(taskPersistenceGateway.existsTaskSameStatement(task.getCourse(), task.getStatement())){
            errors.add(new ErrorItem("statement","O curso não pode ter duas questões com o mesmo enunciado"));
        }
        if(!errors.isEmpty())
            throw new InvalidTaskException(errors);

    }

}
