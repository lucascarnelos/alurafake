package br.com.alura.AluraFake.core.usecase.course;


import br.com.alura.AluraFake.core.exception.UserNotInstructorException;
import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.core.model.user.Role;
import br.com.alura.AluraFake.core.model.user.User;
import br.com.alura.AluraFake.core.usecase.course.impl.CreateCourseUseCaseImpl;
import br.com.alura.AluraFake.core.usecase.course.input.CreateCourseInput;
import br.com.alura.AluraFake.core.usecase.user.VerifyUserExistsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateCourseUseCaseImplTest {

    @Mock private CoursePersistenceGateway coursePersistenceGateway;
    @Mock private VerifyUserExistsUseCase verifyUserExistsUseCase;
    @InjectMocks private CreateCourseUseCaseImpl createCourseUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCourseSuccessfullyWhenInstructorExists() {
        // arrange
        String instructorEmail = "instrutor@alura.com";
        Course draft = new Course(1l, null, null, null, null, Status.BUILDING, null);

        User instructor = new User("name","email", Role.INSTRUCTOR);

        CreateCourseInput input = new CreateCourseInput(draft, instructorEmail);

        when(verifyUserExistsUseCase.execute(instructorEmail)).thenReturn(instructor);

        // act
        assertDoesNotThrow(() -> createCourseUseCase.execute(input));

        // assert
        verify(verifyUserExistsUseCase).execute(instructorEmail);
        verify(coursePersistenceGateway).save(any(Course.class));
    }

    @Test
    void shouldNotCreateCourseWhenInstructorDoesNotExist() {
        // arrange
        String email = "naoexiste@alura.com";
        Course course = new Course(1l, null, null, null, null, Status.BUILDING, null);

        CreateCourseInput input = new CreateCourseInput(course, email);

        when(verifyUserExistsUseCase.execute(email))
                .thenThrow(new UserNotInstructorException("Usuário não encontrado ou não é instrutor"));

        // act + assert
        assertThrows(UserNotInstructorException.class, () -> createCourseUseCase.execute(input));

        verify(verifyUserExistsUseCase).execute(email);
        verify(coursePersistenceGateway, never()).save(any());
    }

}
