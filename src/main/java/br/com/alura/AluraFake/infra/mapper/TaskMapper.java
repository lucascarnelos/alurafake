package br.com.alura.AluraFake.infra.mapper;

import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.infra.api.task.dto.NewTaskDTO;

public class TaskMapper {

    public static Task dtoToDomain(NewTaskDTO newTaskDTO){
        var task = new Task(
                null,
                null,
                null,
                null,
                null,
                null
        );

        var taskOptions = newTaskDTO.options().stream().map(option -> dtoToDomainTaskOption(task, option)).toList();
        return task;
    }

    public static TaskOption dtoToDomainTaskOption(Task task, NewTaskDTO.NewTaskOptionDTO newTaskOptionDTO){
        return new TaskOption(
                null,
                task,
                newTaskOptionDTO.option(),
                newTaskOptionDTO.isCorrect()
        );
    }

}
