package br.com.alura.AluraFake.infra.gateway;

import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.infra.mapper.CourseMapper;
import br.com.alura.AluraFake.infra.mapper.TaskMapper;
import br.com.alura.AluraFake.infra.persistence.task.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskRepositoryPersistenceGateway implements TaskPersistenceGateway {

    private final TaskRepository taskRepository;

    public TaskRepositoryPersistenceGateway(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        var taskEntity = taskRepository.save(TaskMapper.domainToEntity(task));
        return TaskMapper.entityToDomain(taskEntity);
    }

    @Override
    public boolean existsTaskSameStatement(Course course, String statement) {
        return taskRepository.existsByCourseAndStatement(CourseMapper.domainToEntity(course), statement);
    }

    @Override
    public List<Task> findAllByCourseOrderByOrder(Course course) {
        return taskRepository.findAllByCourseOrderByOrder(CourseMapper.domainToEntity(course)).stream().map(TaskMapper::entityToDomain).toList();
    }

    @Override
    public void saveAll(List<Task> orderedTasks) {
        taskRepository.saveAll(orderedTasks.stream().map(TaskMapper::domainToEntity).toList());
    }

}
