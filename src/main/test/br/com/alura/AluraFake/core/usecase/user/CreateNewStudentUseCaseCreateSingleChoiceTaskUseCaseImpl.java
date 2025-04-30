package br.com.alura.AluraFake.core.usecase.user;

import br.com.alura.AluraFake.core.exception.EmailAlreadyExistsException;
import br.com.alura.AluraFake.core.gateway.UserPersistenceGateway;
import br.com.alura.AluraFake.core.model.user.Role;
import br.com.alura.AluraFake.core.model.user.User;
import br.com.alura.AluraFake.core.usecase.user.impl.CreateNewStudentUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CreateNewStudentUseCaseCreateSingleChoiceTaskUseCaseImpl {

    @Mock
    UserPersistenceGateway userPersistenceGateway;
    @InjectMocks
    CreateNewStudentUseCaseImpl createNewStudentUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateNewStudentIfEmailDoesNotExist() {
        User newUser = new User("name","email", Role.STUDENT);

        when(userPersistenceGateway.existsByEmail("email")).thenReturn(false);
        when(userPersistenceGateway.save(newUser)).thenReturn(newUser);

        User result = createNewStudentUseCase.execute(newUser);

        assertEquals(newUser, result);
        verify(userPersistenceGateway).existsByEmail("email");
        verify(userPersistenceGateway).save(newUser);
    }

    @Test
    void shouldThrowExceptionIfEmailAlreadyExists() {
        User existingUser = new User("name","email", Role.STUDENT);

        when(userPersistenceGateway.existsByEmail("email")).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> {
            createNewStudentUseCase.execute(existingUser);
        });

        verify(userPersistenceGateway).existsByEmail("email");
        verify(userPersistenceGateway, never()).save(any());
    }

}
