package br.com.alura.AluraFake.core.gateway;

import br.com.alura.AluraFake.core.model.course.Course;

import java.util.List;

public interface CoursePersistenceGateway {

    Course save(Course course);

    List<Course> findAll();

}
