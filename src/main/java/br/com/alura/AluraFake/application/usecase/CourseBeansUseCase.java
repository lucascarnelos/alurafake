package br.com.alura.AluraFake.application.usecase;

import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.usecase.course.CreateCourseUseCase;
import br.com.alura.AluraFake.core.usecase.course.impl.CreateCourseUseCaseImpl;
import br.com.alura.AluraFake.core.usecase.user.VerifyUserExistsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseBeansUseCase {

    private final CoursePersistenceGateway coursePersistenceGateway;
    private final VerifyUserExistsUseCase verifyUserExistsUseCase;

    public CourseBeansUseCase(CoursePersistenceGateway coursePersistenceGateway, VerifyUserExistsUseCase verifyUserExistsUseCase) {
        this.coursePersistenceGateway = coursePersistenceGateway;
        this.verifyUserExistsUseCase = verifyUserExistsUseCase;
    }

    @Bean
    public CreateCourseUseCase createCourseUseCase(){
        return new CreateCourseUseCaseImpl(coursePersistenceGateway, verifyUserExistsUseCase);
    }

}
