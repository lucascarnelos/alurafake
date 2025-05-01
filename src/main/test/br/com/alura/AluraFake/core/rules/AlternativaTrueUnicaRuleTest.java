package br.com.alura.AluraFake.core.rules;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.rules.task.AlternativaTrueUnicaRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlternativaTrueUnicaRuleTest {

    private AlternativaTrueUnicaRule rule;

    @BeforeEach
    void setUp() {
        rule = new AlternativaTrueUnicaRule();
    }

    @Test
    void shouldAddErrorIfSingleChoiceHasMultipleCorrectAnswers() {
        Task task = new Task();
        task.setType(Type.SINGLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "Opção 1", true),
                new TaskOption(null, null, task, "Opção 2", true) // erro aqui
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldAddErrorsIfMultipleChoiceHasLessThanTwoCorrectOrNoIncorrect() {
        Task task = new Task();
        task.setType(Type.MULTIPLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "A", true),
                new TaskOption(null, null, task, "B", true),
                new TaskOption(null, null, task, "C", true) // todas corretas
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldAddErrorIfMultipleChoiceHasOnlyOneCorrect() {
        Task task = new Task();
        task.setType(Type.MULTIPLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "A", true),
                new TaskOption(null, null, task, "B", false),
                new TaskOption(null, null, task, "C", false)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
    }

    @Test
    void shouldNotAddErrorIfSingleChoiceHasExactlyOneCorrect() {
        Task task = new Task();
        task.setType(Type.SINGLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "A", true),
                new TaskOption(null, null, task, "B", false)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldNotAddErrorIfMultipleChoiceHasAtLeastTwoCorrectAndOneIncorrect() {
        Task task = new Task();
        task.setType(Type.MULTIPLE_CHOICE);
        task.setOptions(List.of(
                new TaskOption(null, null, task, "A", true),
                new TaskOption(null, null, task, "B", true),
                new TaskOption(null, null, task, "C", false)
        ));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldNotFailWithNullOrEmptyOptions() {
        Task task = new Task();
        task.setType(Type.SINGLE_CHOICE);
        task.setOptions(null);

        List<ErrorItem> errors = new ArrayList<>();
        assertDoesNotThrow(() -> rule.execute(errors, task));
        assertTrue(errors.isEmpty());
    }


}
