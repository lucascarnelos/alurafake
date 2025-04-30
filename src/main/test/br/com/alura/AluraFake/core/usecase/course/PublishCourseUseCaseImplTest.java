package br.com.alura.AluraFake.core.usecase.course;

import br.com.alura.AluraFake.core.exception.CourseNotExistsException;
import br.com.alura.AluraFake.core.exception.InvalidCourseException;
import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.usecase.course.impl.PublishCourseUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PublishCourseUseCaseImplTest {

    @Mock private CoursePersistenceGateway coursePersistenceGateway;
    @Mock private TaskPersistenceGateway taskPersistenceGateway;
    @InjectMocks private PublishCourseUseCaseImpl publishCourseUseCase;

    @BeforeEach
    void setUp() {
        coursePersistenceGateway = mock(CoursePersistenceGateway.class);
        taskPersistenceGateway = mock(TaskPersistenceGateway.class);
        publishCourseUseCase = new PublishCourseUseCaseImpl(coursePersistenceGateway, taskPersistenceGateway);
    }

    @Test
    void shouldThrowExceptionWhenCourseNotFound() {
        when(coursePersistenceGateway.findById(1L)).thenReturn(null);

        assertThrows(CourseNotExistsException.class, () -> publishCourseUseCase.execute(1L));
    }

    @Test
    void shouldThrowExceptionWhenCourseIsNotInBuildingStatus() {
        Course course = new Course();
        course.setStatus(Status.PUBLISHED);

        when(coursePersistenceGateway.findById(1L)).thenReturn(course);

        assertThrows(InvalidCourseException.class, () -> publishCourseUseCase.execute(1L));
    }

    @Test
    void shouldThrowExceptionWhenTasksAreInvalid() {
        Course course = new Course();
        course.setStatus(Status.BUILDING);

        Task task1 = new Task(1L, null, Type.SINGLE_CHOICE, course, "A", 1, null);
        Task task2 = new Task(2L, null, Type.MULTIPLE_CHOICE, course, "B", 2, null);
        Task task3 = new Task(3L, null, null, course, "C", 3, null); // faltando tipo

        when(coursePersistenceGateway.findById(1L)).thenReturn(course);
        when(taskPersistenceGateway.findAllByCourseOrderByOrder(course)).thenReturn(List.of(task1, task2, task3));

        assertThrows(InvalidCourseException.class, () -> publishCourseUseCase.execute(1L));
    }

    @Test
    void shouldPublishCourseWhenAllValidationsPass() {
        Course course = spy(new Course());
        course.setStatus(Status.BUILDING);

        Task task1 = new Task(1L, null, Type.OPEN_TEXT, course, "A", 1, null);
        Task task2 = new Task(2L, null, Type.SINGLE_CHOICE, course, "B", 2, null);
        Task task3 = new Task(3L, null, Type.MULTIPLE_CHOICE, course, "C", 3, null);

        when(coursePersistenceGateway.findById(1L)).thenReturn(course);
        when(taskPersistenceGateway.findAllByCourseOrderByOrder(course)).thenReturn(List.of(task1, task2, task3));

        assertDoesNotThrow(() -> publishCourseUseCase.execute(1L));

        verify(course).publish();
        verify(coursePersistenceGateway).save(course);
    }

}
