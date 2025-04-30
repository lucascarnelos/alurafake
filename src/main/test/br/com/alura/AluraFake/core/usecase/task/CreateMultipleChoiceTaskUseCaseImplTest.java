package br.com.alura.AluraFake.core.usecase.task;

import br.com.alura.AluraFake.core.exception.CourseNotExistsException;
import br.com.alura.AluraFake.core.exception.TaskInvalidException;
import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.usecase.task.impl.CreateMultipleChoiceTaskUseCaseImpl;
import br.com.alura.AluraFake.core.usecase.task.impl.CreateMultipleChoiceTaskUseCaseImpl;
import br.com.alura.AluraFake.core.usecase.task.input.CreateTaskInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateMultipleChoiceTaskUseCaseImplTest {

    @Mock private TaskPersistenceGateway taskPersistenceGateway;
    @Mock private CoursePersistenceGateway coursePersistenceGateway;
    @Mock private ValidaTaskUseCase validaTaskUseCase;
    @Mock private OrderingTasksUseCase orderingTasksUseCase;
    @InjectMocks private CreateMultipleChoiceTaskUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateMultipleChoiceTaskSuccessfully() {
        Long courseId = 1L;
        Course course = new Course(courseId, null, null, null, null, Status.BUILDING, null);

        CreateTaskInput input = new CreateTaskInput(
                courseId,
                "Enunciado",
                1,
                List.of(
                        new CreateTaskInput.CreateTaskOption("Opção A", true),
                        new CreateTaskInput.CreateTaskOption("Opção B", true),
                        new CreateTaskInput.CreateTaskOption("Opção C", false)
                )
        );

        when(coursePersistenceGateway.findById(courseId)).thenReturn(course);

        Task savedTask = Task.Create(Type.OPEN_TEXT, course, "Enunciado", 1, null);
        when(taskPersistenceGateway.save(any(Task.class))).thenReturn(savedTask);

        Task result = useCase.execute(input);

        assertNotNull(result);
        assertEquals("Enunciado", result.getStatement());

        verify(validaTaskUseCase).execute(any(Task.class));
        verify(orderingTasksUseCase).execute(any(Task.class));
        verify(taskPersistenceGateway).save(any(Task.class));
    }

    @Test
    void shouldNotCreateMultipleChoiceTaskAndThrowsException() {
        Long courseId = 1L;
        Course course = new Course(courseId, null, null, null, null, Status.BUILDING, null);

        CreateTaskInput input = new CreateTaskInput(
                courseId,
                "Enunciado",
                1,
                List.of(
                        new CreateTaskInput.CreateTaskOption("Opção A", true),
                        new CreateTaskInput.CreateTaskOption("Opção C", true),
                        new CreateTaskInput.CreateTaskOption("Opção C", true),
                        new CreateTaskInput.CreateTaskOption("Enunciado", true),
                        new CreateTaskInput.CreateTaskOption("Enunciado", true),
                        new CreateTaskInput.CreateTaskOption("Enunciado", true)
                )
        );

        when(coursePersistenceGateway.findById(courseId)).thenReturn(course);

        Task savedTask = Task.Create(Type.OPEN_TEXT, course, "Enunciado", 1, null);
        when(taskPersistenceGateway.save(any(Task.class))).thenReturn(savedTask);

        assertThrows(TaskInvalidException.class, () -> useCase.execute(input));
    }

    @Test
    void shouldThrowExceptionWhenCourseNotFound() {
        Long courseId = 1L;
        CreateTaskInput input = new CreateTaskInput(courseId, "Teste", 1, List.of());

        when(coursePersistenceGateway.findById(courseId)).thenReturn(null);

        assertThrows(CourseNotExistsException.class, () -> useCase.execute(input));
    }

    @Test
    void shouldThrowExceptionWhenOptionsAreInvalid() {
        Long courseId = 1L;
        Course course = new Course(courseId, null, null, null, null, Status.BUILDING, null);

        CreateTaskInput input = new CreateTaskInput(
                courseId,
                "Enunciado",
                1,
                List.of(
                        new CreateTaskInput.CreateTaskOption("Opção única", true)
                )
        );

        when(coursePersistenceGateway.findById(courseId)).thenReturn(course);

        assertThrows(TaskInvalidException.class, () -> useCase.execute(input));


        CreateTaskInput input2 = new CreateTaskInput(
                courseId,
                "Enunciado",
                1,
                List.of(
                        new CreateTaskInput.CreateTaskOption("Opção única", true),
                        new CreateTaskInput.CreateTaskOption("Opção única", true),
                        new CreateTaskInput.CreateTaskOption("Opção única", true),
                        new CreateTaskInput.CreateTaskOption("Opção única", true),
                        new CreateTaskInput.CreateTaskOption("Opção única", true),
                        new CreateTaskInput.CreateTaskOption("Opção única", true)
                )
        );
        assertThrows(TaskInvalidException.class, () -> useCase.execute(input2));
    }

}
