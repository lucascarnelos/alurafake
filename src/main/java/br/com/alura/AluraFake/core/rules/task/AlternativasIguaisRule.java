package br.com.alura.AluraFake.core.rules.task;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.rules.ValidationRule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlternativasIguaisRule implements ValidationRule<Task> {


    @Override
    public void execute(List<ErrorItem> errorList, Task task) {
        var taskOptions = task.getOptions();
        Set<String> uniqueOptions = new HashSet<>();

        if(taskOptions != null && !taskOptions.isEmpty()) {
            for (TaskOption taskOption : taskOptions) {
                if(!uniqueOptions.add(taskOption.getOption())){
                    errorList.add(new ErrorItem("option","As alternativas não podem ser iguais entre si."));
                    return;
                }
            }
        }
    }

}
