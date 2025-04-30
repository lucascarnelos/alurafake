package br.com.alura.AluraFake.infra.persistence.task;

import br.com.alura.AluraFake.infra.persistence.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    boolean existsByCourseAndStatement(CourseEntity course, String statement);

    List<TaskEntity> findAllByCourseOrderByOrder(CourseEntity courseEntity);
}
