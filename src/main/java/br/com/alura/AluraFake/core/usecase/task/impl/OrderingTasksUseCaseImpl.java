package br.com.alura.AluraFake.core.usecase.task.impl;

import br.com.alura.AluraFake.core.exception.TaskInvalidException;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.usecase.task.OrderingTasksUseCase;

import java.util.List;

public class OrderingTasksUseCaseImpl implements OrderingTasksUseCase {

    private final TaskPersistenceGateway taskPersistenceGateway;

    public OrderingTasksUseCaseImpl(TaskPersistenceGateway taskPersistenceGateway) {
        this.taskPersistenceGateway = taskPersistenceGateway;
    }

    @Override
    public void execute(Task task) {
        List<Task> orderedTasks = taskPersistenceGateway.findAllByCourseOrderByOrder(task.getCourse());

        int novaOrdem = task.getOrder();

        if (novaOrdem > orderedTasks.size() + 1) {
            throw new TaskInvalidException("Ordem inválida: sequência quebrada");
        }

        for(Task taskOrder : orderedTasks){
            if(taskOrder.getOrder() >= novaOrdem)
                taskOrder.setOrder(taskOrder.getOrder()+1);
        }
        taskPersistenceGateway.saveAll(orderedTasks);
    }

}
