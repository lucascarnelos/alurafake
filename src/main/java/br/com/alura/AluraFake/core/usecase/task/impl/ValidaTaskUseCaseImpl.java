package br.com.alura.AluraFake.core.usecase.task.impl;

import br.com.alura.AluraFake.core.exception.TaskInvalidException;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.usecase.task.ValidaTaskUseCase;

import java.util.ArrayList;
import java.util.List;

public class ValidaTaskUseCaseImpl implements ValidaTaskUseCase {

    private final TaskPersistenceGateway taskPersistenceGateway;

    public ValidaTaskUseCaseImpl(TaskPersistenceGateway taskPersistenceGateway) {
        this.taskPersistenceGateway = taskPersistenceGateway;
    }

    @Override
    public void execute(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        if(task.getOrder() <= 0){
            stringBuilder.append("A ordem deve ser um número inteiro positivo");
        }
        if(!Status.BUILDING.equals(task.getCourse().getStatus())){
            stringBuilder.append("\nUm curso só pode receber atividades se seu status for BULDING");
        }
        if(taskPersistenceGateway.existsTaskSameStatement(task.getCourse(), task.getStatement())){
            stringBuilder.append("\nO curso não pode ter duas questões com o mesmo enunciado");
        }
        if(!stringBuilder.toString().isEmpty())
            throw new TaskInvalidException(stringBuilder.toString());

    }

}
