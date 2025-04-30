package br.com.alura.AluraFake.application.usecase;

import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.usecase.task.CreateOpenTextTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.OrderingTasksUseCase;
import br.com.alura.AluraFake.core.usecase.task.ValidaTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.impl.CreateOpenTextTaskUseCaseImpl;
import br.com.alura.AluraFake.core.usecase.task.impl.OrderingTasksUseCaseImpl;
import br.com.alura.AluraFake.core.usecase.task.impl.ValidaTaskUseCaseImpl;
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
    public ValidaTaskUseCase validaTaskUseCase(){
        return new ValidaTaskUseCaseImpl(taskPersistenceGateway);
    }

    @Bean
    public OrderingTasksUseCase orderingTasksUseCase(){
        return new OrderingTasksUseCaseImpl(taskPersistenceGateway);
    }

}
