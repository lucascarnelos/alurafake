package br.com.alura.AluraFake.core.rules;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.rules.task.AlternativasIguaisRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlternativasIguaisRuleTest {

    private AlternativasIguaisRule rule;

    @BeforeEach
    void setUp() {
        rule = new AlternativasIguaisRule();
    }

    @Test
    void shouldAddErrorWhenDuplicateOptionsExist() {
        Task task = new Task();
        task.setOptions(List.of(
                new TaskOption(null, null, task, "Opção A", false),
                new TaskOption(null, null, task, "Opção B", false),
                new TaskOption(null, null, task, "Opção A", true) // duplicada
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldNotAddErrorWhenAllOptionsAreUnique() {
        Task task = new Task();
        task.setOptions(List.of(
                new TaskOption(null, null, task, "A", false),
                new TaskOption(null, null, task, "B", false),
                new TaskOption(null, null, task, "C", true)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldNotAddErrorWhenOptionListIsEmpty() {
        Task task = new Task();
        task.setOptions(List.of());

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldNotThrowOnNullOptionList() {
        Task task = new Task();
        task.setOptions(null);

        List<ErrorItem> errors = new ArrayList<>();
        assertDoesNotThrow(() -> rule.execute(errors, task));
        assertTrue(errors.isEmpty());
    }

}
