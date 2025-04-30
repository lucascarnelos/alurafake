package br.com.alura.AluraFake.core.usecase.task;

import br.com.alura.AluraFake.core.exception.TaskInvalidException;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.usecase.task.impl.OrderingTasksUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrderingTasksUseCaseImplCreateSingleChoiceTaskUseCaseImpl {

    @Mock
    private TaskPersistenceGateway taskPersistenceGateway;
    @InjectMocks private OrderingTasksUseCaseImpl orderingTasksUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldShiftTasksWhenNewTaskInsertedInMiddle() {
        Course course = new Course();
        Task task1 = new Task(1L, null,null,  course, "A", 1, null);
        Task task2 = new Task(2L, null, null, course, "B", 2, null);
        Task task3 = new Task(3L, null, null, course, "C", 3, null);
        Task novaTask = new Task(null, null, null, course, "Nova", 2, null);

        when(taskPersistenceGateway.findAllByCourseOrderByOrder(course)).thenReturn(List.of(task1, task2, task3));

        orderingTasksUseCase.execute(novaTask);

        assertEquals(3, task2.getOrder());
        assertEquals(4, task3.getOrder());
        verify(taskPersistenceGateway).saveAll(List.of(task1, task2, task3));
    }

    @Test
    void shouldThrowExceptionWhenOrderBreaksSequence() {
        Course course = new Course();
        Task task1 = new Task(1L,null, null, course, "A", 1, null);
        Task task2 = new Task(2L, null, null, course, "B", 2, null);
        Task novaTask = new Task(null,null,  null, course, "Nova", 5, null);

        when(taskPersistenceGateway.findAllByCourseOrderByOrder(course)).thenReturn(List.of(task1, task2));

        assertThrows(TaskInvalidException.class, () -> orderingTasksUseCase.execute(novaTask));
    }

    @Test
    void shouldThrowExceptionWhenOrderIsZero() {
        Course course = new Course();
        Task task1 = new Task(1L,null, null, course, "A", 1, null);
        Task task2 = new Task(2L, null, null, course, "B", 2, null);
        Task novaTask = new Task(null,null,  null, course, "Nova", 0, null);

        when(taskPersistenceGateway.findAllByCourseOrderByOrder(course)).thenReturn(List.of(task1, task2));

        assertThrows(TaskInvalidException.class, () -> orderingTasksUseCase.execute(novaTask));
    }

    @Test
    void shouldThrowExceptionWhenOrderIsNegative() {
        Course course = new Course();
        Task task1 = new Task(1L,null, null, course, "A", 1, null);
        Task task2 = new Task(2L, null, null, course, "B", 2, null);
        Task novaTask = new Task(null,null,  null, course, "Nova", -1, null);

        when(taskPersistenceGateway.findAllByCourseOrderByOrder(course)).thenReturn(List.of(task1, task2));

        assertThrows(TaskInvalidException.class, () -> orderingTasksUseCase.execute(novaTask));
    }



}
