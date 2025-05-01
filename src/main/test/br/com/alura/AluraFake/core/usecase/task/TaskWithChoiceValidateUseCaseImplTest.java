package br.com.alura.AluraFake.core.usecase.task;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.exception.InvalidTaskException;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.rules.ValidationRule;
import br.com.alura.AluraFake.core.usecase.task.impl.TaskWithChoiceValidateUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class TaskWithChoiceValidateUseCaseImplTest {

    private TaskWithChoiceValidateUseCaseImpl useCase;

    private ValidationRule<Task> rule1;
    private ValidationRule<Task> rule2;
    private ValidationRule<Task> rule3;
    private ValidationRule<Task> rule4;
    private ValidationRule<Task> rule5;

    @BeforeEach
    void setUp() {
        useCase = new TaskWithChoiceValidateUseCaseImpl();

        rule1 = mock(ValidationRule.class);
        rule2 = mock(ValidationRule.class);
        rule3 = mock(ValidationRule.class);
        rule4 = mock(ValidationRule.class);
        rule5 = mock(ValidationRule.class);
    }

    @Test
    void shouldNotThrowWhenAllRulesPass() {
        Task task = new Task();

        // substituicao das rules da classe real por regras mockadas
        var useCase = new TaskWithChoiceValidateUseCaseImpl() {
            @Override
            public void execute(Task task) {
                List<ErrorItem> errors = new ArrayList<>();
                for (ValidationRule<Task> rule : List.of(rule1, rule2, rule3, rule4, rule5)) {
                    rule.execute(errors, task);
                }
                if (!errors.isEmpty()) throw new InvalidTaskException(errors);
            }
        };

        assertDoesNotThrow(() -> useCase.execute(task));

        verify(rule1).execute(anyList(), eq(task));
        verify(rule2).execute(anyList(), eq(task));
        verify(rule3).execute(anyList(), eq(task));
        verify(rule4).execute(anyList(), eq(task));
        verify(rule5).execute(anyList(), eq(task));
    }

    @Test
    void shouldThrowWhenAtLeastOneRuleAddsError() {
        Task task = new Task();

        doAnswer(invocation -> {
            List<ErrorItem> errors = invocation.getArgument(0);
            errors.add(new ErrorItem("option", "Erro simulado"));
            return null;
        }).when(rule3).execute(anyList(), eq(task));

        var useCase = new TaskWithChoiceValidateUseCaseImpl() {
            @Override
            public void execute(Task task) {
                List<ErrorItem> errors = new ArrayList<>();
                for (ValidationRule<Task> rule : List.of(rule1, rule2, rule3, rule4, rule5)) {
                    rule.execute(errors, task);
                }
                if (!errors.isEmpty()) throw new InvalidTaskException(errors);
            }
        };

        InvalidTaskException ex = assertThrows(InvalidTaskException.class, () -> useCase.execute(task));
        assertEquals(1, ex.getErrors().size());
    }

}
