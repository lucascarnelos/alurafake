package br.com.alura.AluraFake.core.rules;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.rules.task.MaximoAlternativasTaskRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaximoAlternativasTaskRuleTest {

    private MaximoAlternativasTaskRule rule;

    @BeforeEach
    void setUp() {
        rule = new MaximoAlternativasTaskRule();
    }

    @Test
    void shouldAddErrorIfSingleChoiceHasLessThan2Options() {
        Task task = new Task();
        task.setType(Type.SINGLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "Opção única", true)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldAddErrorIfMultipleChoiceHasLessThan3Options() {
        Task task = new Task();
        task.setType(Type.MULTIPLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "A", true),
                new TaskOption(null, null, task, "B", false)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldAddErrorIfOptionsAreMoreThan5() {
        Task task = new Task();
        task.setType(Type.SINGLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "1", false),
                new TaskOption(null, null, task, "2", false),
                new TaskOption(null, null, task, "3", false),
                new TaskOption(null, null, task, "4", false),
                new TaskOption(null, null, task, "5", false),
                new TaskOption(null, null, task, "6", false)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldNotAddErrorIfSingleChoiceHasBetween2And5Options() {
        Task task = new Task();
        task.setType(Type.SINGLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "1", false),
                new TaskOption(null, null, task, "2", true)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldNotAddErrorIfMultipleChoiceHasBetween3And5Options() {
        Task task = new Task();
        task.setType(Type.MULTIPLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "1", true),
                new TaskOption(null, null, task, "2", true),
                new TaskOption(null, null, task, "3", false)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldAddErrorIfOptionsListIsNull() {
        Task task = new Task();
        task.setType(Type.MULTIPLE_CHOICE);
        task.setOptions(null);

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

}
