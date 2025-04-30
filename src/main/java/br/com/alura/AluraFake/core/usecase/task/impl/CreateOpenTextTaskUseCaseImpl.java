package br.com.alura.AluraFake.core.usecase.task.impl;

import br.com.alura.AluraFake.core.exception.CourseNotExistsException;
import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.usecase.task.CreateOpenTextTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.OrderingTasksUseCase;
import br.com.alura.AluraFake.core.usecase.task.ValidaTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.input.CreateTaskInput;

public class CreateOpenTextTaskUseCaseImpl implements CreateOpenTextTaskUseCase {

    private final TaskPersistenceGateway taskPersistenceGateway;
    private final CoursePersistenceGateway coursePersistenceGateway;
    private final ValidaTaskUseCase validaTaskUseCase;
    private final OrderingTasksUseCase orderingTasksUseCase;

    public CreateOpenTextTaskUseCaseImpl(TaskPersistenceGateway taskPersistenceGateway, CoursePersistenceGateway coursePersistenceGateway, ValidaTaskUseCase validaTaskUseCase, OrderingTasksUseCase orderingTasksUseCase) {
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
                Type.OPEN_TEXT,
                course,
                input.statement(),
                input.order(),
                null
        );

        validaTaskUseCase.execute(task);
        orderingTasksUseCase.execute(task);
        task = taskPersistenceGateway.save(task);
        return task;
    }
}
