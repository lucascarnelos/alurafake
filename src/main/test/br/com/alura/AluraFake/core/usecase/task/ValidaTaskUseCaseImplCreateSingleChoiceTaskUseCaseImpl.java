package br.com.alura.AluraFake.core.usecase.task;

import br.com.alura.AluraFake.core.exception.TaskInvalidException;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.usecase.task.impl.ValidaTaskUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidaTaskUseCaseImplCreateSingleChoiceTaskUseCaseImpl {

    @Mock
    private TaskPersistenceGateway taskPersistenceGateway;
    @InjectMocks private ValidaTaskUseCaseImpl validaTaskUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPassValidationForValidTask() {
        Course course = new Course();
        course.setStatus(Status.BUILDING);

        Task task = new Task(1L, null,null,  course, "Questão válida", 1, null);

        when(taskPersistenceGateway.existsTaskSameStatement(course, "Questão válida")).thenReturn(false);

        assertDoesNotThrow(() -> validaTaskUseCase.execute(task));
    }

    @Test
    void shouldThrowExceptionWhenOrderIsInvalid() {
        Course course = new Course();
        course.setStatus(Status.BUILDING);

        Task task = new Task(1L, null,null,  course, "Questão inválida", -1, null);

        when(taskPersistenceGateway.existsTaskSameStatement(course, "Questão inválida")).thenReturn(false);

        assertThrows(TaskInvalidException.class, () -> validaTaskUseCase.execute(task));
    }

    @Test
    void shouldThrowExceptionWhenCourseStatusIsNotBuilding() {
        Course course = new Course();
        course.setStatus(Status.PUBLISHED);

        Task task = new Task(1L, null,null,  course, "Questão com curso publicado", 1, null);

        when(taskPersistenceGateway.existsTaskSameStatement(course, "Questão com curso publicado")).thenReturn(false);

        assertThrows(TaskInvalidException.class, () -> validaTaskUseCase.execute(task));
    }

    @Test
    void shouldThrowExceptionWhenStatementAlreadyExists() {
        Course course = new Course();
        course.setStatus(Status.BUILDING);

        Task task = new Task(1L, null,null,  course, "Repetida", 1, null);

        when(taskPersistenceGateway.existsTaskSameStatement(course, "Repetida")).thenReturn(true);

        assertThrows(TaskInvalidException.class, () -> validaTaskUseCase.execute(task));
    }

}
