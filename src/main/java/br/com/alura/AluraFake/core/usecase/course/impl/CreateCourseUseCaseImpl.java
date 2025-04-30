package br.com.alura.AluraFake.core.usecase.course.impl;

import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.usecase.course.CreateCourseUseCase;
import br.com.alura.AluraFake.core.usecase.course.input.CreateCourseInput;
import br.com.alura.AluraFake.core.usecase.user.VerifyUserExistsUseCase;

public class CreateCourseUseCaseImpl implements CreateCourseUseCase {

    private final CoursePersistenceGateway coursePersistenceGateway;
    private final VerifyUserExistsUseCase verifyUserExistsUseCase;

    public CreateCourseUseCaseImpl(CoursePersistenceGateway coursePersistenceGateway, VerifyUserExistsUseCase verifyUserExistsUseCase) {
        this.coursePersistenceGateway = coursePersistenceGateway;
        this.verifyUserExistsUseCase = verifyUserExistsUseCase;
    }

    @Override
    public void execute(CreateCourseInput input) {
        var user = verifyUserExistsUseCase.execute(input.instructorEmail());
        Course course = Course.Create(input.course().getTitle(), input.course().getDescription(), user);
        coursePersistenceGateway.save(course);
    }

}
