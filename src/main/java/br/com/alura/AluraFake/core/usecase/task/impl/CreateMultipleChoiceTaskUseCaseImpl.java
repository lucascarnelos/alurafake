package br.com.alura.AluraFake.core.usecase.task.impl;

import br.com.alura.AluraFake.core.exception.CourseNotExistsException;
import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.usecase.task.CreateMultipleChoiceTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.OrderingTasksUseCase;
import br.com.alura.AluraFake.core.usecase.task.TaskWithChoiceValidateUseCase;
import br.com.alura.AluraFake.core.usecase.task.GenericTaskValidateUseCase;
import br.com.alura.AluraFake.core.usecase.task.input.CreateTaskInput;

public class CreateMultipleChoiceTaskUseCaseImpl implements CreateMultipleChoiceTaskUseCase {

    private final TaskPersistenceGateway taskPersistenceGateway;
    private final CoursePersistenceGateway coursePersistenceGateway;
    private final GenericTaskValidateUseCase genericTaskValidateUseCase;
    private final OrderingTasksUseCase orderingTasksUseCase;
    private final TaskWithChoiceValidateUseCase taskWithChoiceValidateUseCase;

    public CreateMultipleChoiceTaskUseCaseImpl(TaskPersistenceGateway taskPersistenceGateway, CoursePersistenceGateway coursePersistenceGateway, GenericTaskValidateUseCase genericTaskValidateUseCase, OrderingTasksUseCase orderingTasksUseCase, TaskWithChoiceValidateUseCase taskWithChoiceValidateUseCase) {
        this.taskPersistenceGateway = taskPersistenceGateway;
        this.coursePersistenceGateway = coursePersistenceGateway;
        this.genericTaskValidateUseCase = genericTaskValidateUseCase;
        this.orderingTasksUseCase = orderingTasksUseCase;
        this.taskWithChoiceValidateUseCase = taskWithChoiceValidateUseCase;
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
        taskWithChoiceValidateUseCase.execute(task);
        genericTaskValidateUseCase.execute(task);
        orderingTasksUseCase.execute(task);
        return taskPersistenceGateway.save(task);
    }

}
