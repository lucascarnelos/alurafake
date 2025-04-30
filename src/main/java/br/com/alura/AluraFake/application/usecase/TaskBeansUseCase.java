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
    public CreateOpenTextTaskUseCase createOpenTextTaskUseCase(ValidaTaskUseCase validaTaskUseCase, OrderingTasksUseCase orderingTasksUseCase){
        return new CreateOpenTextTaskUseCaseImpl(taskPersistenceGateway,coursePersistenceGateway,validaTaskUseCase, orderingTasksUseCase);
    }

    @Bean
    public CreateSingleChoiceTaskUseCase createSingleChoiceTaskUseCase(ValidaTaskUseCase validaTaskUseCase, OrderingTasksUseCase orderingTasksUseCase){
        return new CreateSingleChoiceTaskUseCaseImpl(taskPersistenceGateway,coursePersistenceGateway,validaTaskUseCase, orderingTasksUseCase);
    }

    @Bean
    public CreateMultipleChoiceTaskUseCase createMultipleChoiceTaskUseCase(ValidaTaskUseCase validaTaskUseCase, OrderingTasksUseCase orderingTasksUseCase){
        return new CreateMultipleChoiceTaskUseCaseImpl(taskPersistenceGateway,coursePersistenceGateway,validaTaskUseCase, orderingTasksUseCase);
    }


    @Bean
    public ValidaTaskUseCase validaTaskUseCase(){
        return new ValidaTaskUseCaseImpl(taskPersistenceGateway);
    }

    @Bean
    public OrderingTasksUseCase orderingTasksUseCase(){
        return new OrderingTasksUseCaseImpl(taskPersistenceGateway);
    }

}
