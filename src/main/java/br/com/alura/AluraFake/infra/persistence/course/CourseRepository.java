package br.com.alura.AluraFake.infra.persistence.course;

import br.com.alura.AluraFake.core.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
