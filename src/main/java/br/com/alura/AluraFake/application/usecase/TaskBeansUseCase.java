package br.com.alura.AluraFake.application.usecase;

import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.usecase.task.*;
import br.com.alura.AluraFake.core.usecase.task.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskBeansUseCase {

    private final TaskPersistenceGateway taskPersistenceGateway;
    private final CoursePersistenceGateway coursePersistenceGateway;

    public TaskBeansUseCase(TaskPersistenceGateway taskPersistenceGateway, CoursePersistenceGateway coursePersistenceGateway) {
        this.taskPersistenceGateway = taskPersistenceGateway;
        this.coursePersistenceGateway = coursePersistenceGateway;
    }

    @Bean
    public CreateOpenTextTaskUseCase createOpenTextTaskUseCase(GenericTaskValidateUseCase genericTaskValidateUseCase, OrderingTasksUseCase orderingTasksUseCase){
        return new CreateOpenTextTaskUseCaseImpl(taskPersistenceGateway,coursePersistenceGateway, genericTaskValidateUseCase, orderingTasksUseCase);
    }

    @Bean
    public CreateSingleChoiceTaskUseCase createSingleChoiceTaskUseCase(GenericTaskValidateUseCase genericTaskValidateUseCase, OrderingTasksUseCase orderingTasksUseCase, TaskWithChoiceValidateUseCase taskWithChoiceValidateUseCase){
        return new CreateSingleChoiceTaskUseCaseImpl(taskPersistenceGateway,coursePersistenceGateway, genericTaskValidateUseCase, orderingTasksUseCase, taskWithChoiceValidateUseCase);
    }

    @Bean
    public CreateMultipleChoiceTaskUseCase createMultipleChoiceTaskUseCase(GenericTaskValidateUseCase genericTaskValidateUseCase, OrderingTasksUseCase orderingTasksUseCase, TaskWithChoiceValidateUseCase taskWithChoiceValidateUseCase){
        return new CreateMultipleChoiceTaskUseCaseImpl(taskPersistenceGateway,coursePersistenceGateway, genericTaskValidateUseCase, orderingTasksUseCase, taskWithChoiceValidateUseCase);
    }

    @Bean
    public GenericTaskValidateUseCase validaTaskUseCase(){
        return new GerenicGenericTaskValidateUseCaseImpl(taskPersistenceGateway);
    }

    @Bean
    public TaskWithChoiceValidateUseCase taskWithChoiceValidateUseCase(){
        return new TaskWithChoiceValidateUseCaseImpl();
    }

    @Bean
    public OrderingTasksUseCase orderingTasksUseCase(){
        return new OrderingTasksUseCaseImpl(taskPersistenceGateway);
    }

}
