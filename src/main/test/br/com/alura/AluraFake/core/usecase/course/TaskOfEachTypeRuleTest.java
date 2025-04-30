package br.com.alura.AluraFake.core.usecase.course;

import br.com.alura.AluraFake.core.exception.InvalidCourseException;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.usecase.course.rules.TaskOfEachTypeRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskOfEachTypeRuleTest {

    @InjectMocks
    TaskOfEachTypeRule rule;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldNotThrowWhenAllThreeTypesExist() {
        Task task1 = new Task(1L, null, Type.OPEN_TEXT, null, "A", 1, null);
        Task task2 = new Task(2L, null, Type.SINGLE_CHOICE, null, "B", 2, null);
        Task task3 = new Task(3L, null, Type.MULTIPLE_CHOICE, null, "C", 3, null);

        assertDoesNotThrow(() -> rule.execute(List.of(task1, task2, task3)));
    }

    @Test
    void shouldThrowWhenOneTypeIsMissing() {
        Task task1 = new Task(1L, null, Type.SINGLE_CHOICE, null, "A", 1, null);
        Task task2 = new Task(2L, null, Type.MULTIPLE_CHOICE, null, "B", 2, null);
        Task task3 = new Task(3L, null, null, null, "C", 3, null); // null não conta

        assertThrows(InvalidCourseException.class, () -> rule.execute(List.of(task1, task2, task3)));
    }

    @Test
    void shouldThrowWhenListIsEmpty() {
        assertThrows(InvalidCourseException.class, () -> rule.execute(List.of()));
    }

    @Test
    void shouldIgnoreDuplicatesAndNullTypes() {
        Task task1 = new Task(1L, null, Type.OPEN_TEXT, null, "A", 1, null);
        Task task2 = new Task(2L, null, Type.SINGLE_CHOICE, null, "B", 2, null);
        Task task3 = new Task(3L, null, Type.SINGLE_CHOICE, null, "Duplicada", 3, null);
        Task task4 = new Task(4L, null, null, null, "Sem tipo", 4, null);
        Task task5 = new Task(5L, null, Type.MULTIPLE_CHOICE, null, "C", 5, null);

        assertDoesNotThrow(() -> rule.execute(List.of(task1, task2, task3, task4, task5)));
    }

}
