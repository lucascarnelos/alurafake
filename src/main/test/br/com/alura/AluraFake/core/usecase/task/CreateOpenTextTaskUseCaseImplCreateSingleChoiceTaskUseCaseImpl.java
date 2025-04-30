package br.com.alura.AluraFake.core.usecase.task;

import br.com.alura.AluraFake.core.exception.CourseNotExistsException;
import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.usecase.task.impl.CreateOpenTextTaskUseCaseImpl;
import br.com.alura.AluraFake.core.usecase.task.input.CreateTaskInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateOpenTextTaskUseCaseImplCreateSingleChoiceTaskUseCaseImpl {

    @Mock private TaskPersistenceGateway taskPersistenceGateway;
    @Mock private CoursePersistenceGateway coursePersistenceGateway;
    @Mock private ValidaTaskUseCase validaTaskUseCase;
    @Mock private OrderingTasksUseCase orderingTasksUseCase;
    @InjectMocks private CreateOpenTextTaskUseCaseImpl createOpenTextTaskUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateOpenTextTaskSuccessfully() {
        Long courseId = 1L;
        Course course = new Course(courseId, null, null, null, null, Status.BUILDING, null);

        CreateTaskInput input = new CreateTaskInput(courseId, "Enunciado", 1, null);

        Task createdTask = Task.Create(Type.OPEN_TEXT, course, "Enunciado", 1, null);

        when(coursePersistenceGateway.findById(courseId)).thenReturn(course);
        when(taskPersistenceGateway.save(any(Task.class))).thenReturn(createdTask);

        Task result = createOpenTextTaskUseCase.execute(input);

        assertNotNull(result);
        assertEquals("Enunciado", result.getStatement());

        verify(validaTaskUseCase).execute(any(Task.class));
        verify(orderingTasksUseCase).execute(any(Task.class));
        verify(taskPersistenceGateway).save(any(Task.class));
    }

    @Test
    void shouldThrowExceptionWhenCourseDoesNotExist() {
        Long courseId = 1L;
        CreateTaskInput input = new CreateTaskInput(courseId, "Questão", 1, null);

        when(coursePersistenceGateway.findById(courseId)).thenReturn(null);

        assertThrows(CourseNotExistsException.class, () -> createOpenTextTaskUseCase.execute(input));
    }

}
