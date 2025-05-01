package br.com.alura.AluraFake.core.rules.task;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.rules.ValidationRule;

import java.util.List;

public class MaxCaracterRule implements ValidationRule<Task> {
    @Override
    public void execute(List<ErrorItem> errorList, Task task) {
        var taskOptions = task.getOptions();
        if(taskOptions != null && !taskOptions.isEmpty()) {
            for (TaskOption taskOption : taskOptions) {
                if (taskOption.getOption() == null || !(taskOption.getOption().length() >= 4 && taskOption.getOption().length() <= 80)){
                    errorList.add(new ErrorItem("option","As alternativas devem ter no mínimo 4 e no máximo 80 caracteres."));
                    return;
                }
            }
        }
    }
}
