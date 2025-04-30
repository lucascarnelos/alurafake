package br.com.alura.AluraFake.core.gateway;

import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.task.Task;

import java.util.List;

public interface TaskPersistenceGateway {

    Task save(Task task);

    boolean existsTaskSameStatement(Course course, String statement);

    List<Task> findAllByCourseOrderByOrder(Course course);

    void saveAll(List<Task> orderedTasks);
}
