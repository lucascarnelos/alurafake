package br.com.alura.AluraFake.infra.mapper;

import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.infra.api.course.dto.NewCourseDTO;
import br.com.alura.AluraFake.infra.persistence.course.CourseEntity;
import br.com.alura.AluraFake.infra.persistence.user.UserEntity;

import java.time.LocalDateTime;

public class CourseMapper {

    public static CourseEntity domainToEntity(Course course){
        return new CourseEntity(
                course.getId(),
                course.getCreatedAt(),
                course.getTitle(),
                course.getDescription(),
                UserMapper.domainToEntity(course.getInstructor()),
                course.getStatus(),
                course.getPublishedAt()
        );
    }

    public static Course entityToDomain(CourseEntity course){
        return new Course(
                course.getId(),
                course.getCreatedAt(),
                course.getTitle(),
                course.getDescription(),
                UserMapper.entityToDomain(course.getInstructor()),
                course.getStatus(),
                course.getPublishedAt()
        );
    }

    public static Course dtoToDomain(NewCourseDTO newCourseDTO){
        return new Course(
                null,
                null,
                newCourseDTO.getTitle(),
                newCourseDTO.getDescription(),
                null,
                null,
                null
        );
    }

}
