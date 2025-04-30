package br.com.alura.AluraFake.core.usecase.task.impl;

import br.com.alura.AluraFake.core.exception.ErrorItem;
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

        if(task.getOrder() == null || task.getOrder() <= 0)
            throw new TaskInvalidException(List.of(new ErrorItem("order","Ordem inválida: não pode ser uma ordem nula e nem menor ou igual a 0 (zero)")));

        int novaOrdem = task.getOrder();

        if (novaOrdem > orderedTasks.size() + 1) {
            throw new TaskInvalidException(List.of(new ErrorItem("order","Ordem inválida: sequência quebrada")));
        }

        for(Task taskOrder : orderedTasks){
            if(taskOrder.getOrder() >= novaOrdem)
                taskOrder.setOrder(taskOrder.getOrder()+1);
        }
        taskPersistenceGateway.saveAll(orderedTasks);
    }

}
