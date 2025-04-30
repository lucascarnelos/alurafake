package br.com.alura.AluraFake.core.usecase.user;

import br.com.alura.AluraFake.core.exception.UserNotInstructorException;
import br.com.alura.AluraFake.core.gateway.UserPersistenceGateway;
import br.com.alura.AluraFake.core.model.user.Role;
import br.com.alura.AluraFake.core.model.user.User;
import br.com.alura.AluraFake.core.usecase.user.impl.VerifyUserExistsUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VerifyUserExistsUseCaseCreateSingleChoiceTaskUseCaseImpl {

    @Mock
    UserPersistenceGateway userPersistenceGateway;
    @InjectMocks
    VerifyUserExistsUseCaseImpl verifyUserExistsUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUserWhenInstructorExists() {
        User instructor = new User("name","email", Role.INSTRUCTOR);

        when(userPersistenceGateway.findByEmail("email")).thenReturn(Optional.of(instructor));

        User result = verifyUserExistsUseCase.execute("email");

        assertEquals(instructor, result);
        verify(userPersistenceGateway, times(1)).findByEmail("email");
        assertTrue(instructor.isInstructor());
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExistOrIsNotInstructor() {
        String email = "emailNaoExiste";
        when(userPersistenceGateway.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNotInstructorException.class, () -> verifyUserExistsUseCase.execute(email));

        String emailDoUserQueExiste = "emailexistente";
        User student = new User("name",emailDoUserQueExiste, Role.STUDENT);
        when(userPersistenceGateway.findByEmail(emailDoUserQueExiste)).thenReturn(Optional.of(student));

        assertThrows(UserNotInstructorException.class, () -> verifyUserExistsUseCase.execute(emailDoUserQueExiste));
    }

}
