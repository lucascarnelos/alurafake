package br.com.alura.AluraFake.core.rules;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.rules.task.AlternativaIgualEnunciadoTaskRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlternativaIgualEnunciadoTaskRuleTest {

    @InjectMocks
    AlternativaIgualEnunciadoTaskRule rule;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddErrorWhenOptionEqualsStatement() {
        Task task = new Task();
        task.setStatement("Qual é a capital da França?");

        TaskOption option1 = new TaskOption(null,null, null, "Qual é a capital da França?", false);
        TaskOption option2 = new TaskOption(null, null, null, "Berlim", false);

        task.setOptions(List.of(option1, option2));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertEquals(1, errors.size());
        assertEquals("option", errors.get(0).getField());
    }

    @Test
    void shouldNotAddErrorWhenOptionsAreDifferent() {
        Task task = new Task();
        task.setStatement("Qual é a capital da França?");

        TaskOption option1 = new TaskOption(null, null, null, "Paris", true);
        TaskOption option2 = new TaskOption(null, null,null, "Berlim", false);

        task.setOptions(List.of(option1, option2));

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldNotFailOnNullOptions() {
        Task task = new Task();
        task.setStatement("Qual é a capital da França?");
        task.setOptions(null); // lista nula

        List<ErrorItem> errors = new ArrayList<>();
        assertDoesNotThrow(() -> rule.execute(errors, task));
        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldNotFailOnEmptyOptions() {
        Task task = new Task();
        task.setStatement("Qual é a capital da França?");
        task.setOptions(List.of()); // lista vazia

        List<ErrorItem> errors = new ArrayList<>();
        rule.execute(errors, task);

        assertTrue(errors.isEmpty());
    }

}
