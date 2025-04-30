package br.com.alura.AluraFake.core.usecase.course.input;

import br.com.alura.AluraFake.core.model.course.Course;

public record CreateCourseInput(
        Course course,
        String instructorEmail
){

}
