package br.com.alura.AluraFake.infra.gateway;

import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.infra.mapper.CourseMapper;
import br.com.alura.AluraFake.infra.persistence.course.CourseEntity;
import br.com.alura.AluraFake.infra.persistence.course.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseRepositoryPersistenceGateway implements CoursePersistenceGateway {

    private final CourseRepository courseRepository;

    public CourseRepositoryPersistenceGateway(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course save(Course course) {
        CourseEntity courseEntity = courseRepository.save(CourseMapper.domainToEntity(course));
        return CourseMapper.entityToDomain(courseEntity);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll().stream().map(CourseMapper::entityToDomain).toList();
    }
}
