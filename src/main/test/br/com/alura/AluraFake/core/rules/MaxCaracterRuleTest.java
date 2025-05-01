package br.com.alura.AluraFake.core.rules;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.rules.task.MaxCaracterRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaxCaracterRuleTest {

    private MaxCaracterRule rule;

    @BeforeEach
    void setUp() {
        rule = new MaxCaracterRule();
    }

    @Test
    void shouldAddErrorWhenOptionIsTooShort() {
        Task task = new Task();
        task.setOptions(List.of(
                new TaskOption(null, null, task, "ABC", false) // 3 caracteres
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldAddErrorWhenOptionIsTooLong() {
        String longText = "A".repeat(81);
        Task task = new Task();
        task.setOptions(List.of(
                new TaskOption(null, null, task, longText, false)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldAddErrorWhenOptionIsNull() {
        Task task = new Task();
        task.setOptions(List.of(
                new TaskOption(null, null, task, null, false)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldNotAddErrorWhenAllOptionsAreWithinLimits() {
        Task task = new Task();
        task.setOptions(List.of(
                new TaskOption(null, null, task, "Opção válida", true),
                new TaskOption(null, null, task, "1234", false),
                new TaskOption(null, null, task, "A".repeat(80), false)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldNotFailWhenOptionsAreNullOrEmpty() {
        Task task = new Task();
        task.setOptions(null);

        List<ErrorItem> errors = new ArrayList<>();
        assertDoesNotThrow(() -> rule.execute(errors, task));
        assertTrue(errors.isEmpty());

        task.setOptions(List.of());
        rule.execute(errors, task);
        assertTrue(errors.isEmpty());
    }

}
