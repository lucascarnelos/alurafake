package br.com.alura.AluraFake.infra.mapper;

import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.infra.persistence.task.TaskEntity;
import br.com.alura.AluraFake.infra.persistence.task.TaskOptionEntity;

import java.util.List;

public class TaskMapper {

    public static TaskEntity domainToEntity(Task task){
        var taskEntity = new TaskEntity(
                task.getId(),
                task.getCreatedAt(),
                task.getType(),
                CourseMapper.domainToEntity(task.getCourse()),
                task.getStatement(),
                task.getOrder(),
                null
        );

        if (task.getOptions() != null && !task.getOptions().isEmpty()) {
            List<TaskOptionEntity> options = task.getOptions().stream()
                    .map(option -> {
                        return new TaskOptionEntity(
                                option.getId(),
                                option.getCreatedAt(),
                                null,
                                option.getOption(),
                                option.getCorrect()
                        );
                    })
                    .toList();
            options.forEach(option -> option.setTask(taskEntity));
            taskEntity.setOptions(options);
        }
        return taskEntity;
    }

    public static Task entityToDomain(TaskEntity taskEntity){
        var task = new Task(
                taskEntity.getId(),
                taskEntity.getCreatedAt(),
                taskEntity.getType(),
                CourseMapper.entityToDomain(taskEntity.getCourse()),
                taskEntity.getStatement(),
                taskEntity.getOrder(),
                null
        );
        if (taskEntity.getOptions() != null && !taskEntity.getOptions().isEmpty()) {
            List<TaskOption> options = taskEntity.getOptions().stream()
                    .map(option -> {
                        return new TaskOption(
                                option.getId(),
                                option.getCreatedAt(),
                                null,
                                option.getOption(),
                                option.getCorrect()
                        );
                    })
                    .toList();
            options.forEach(option -> option.setTask(task));
            task.setOptions(options);
        }
        return task;
    }

}
