package br.com.alura.AluraFake.core.rules.task;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.rules.ValidationRule;

import java.util.List;

public class AlternativaIgualEnunciadoTaskRule implements ValidationRule<Task> {
    @Override
    public void execute(List<ErrorItem> errorList, Task task) {
        var taskOptions = task.getOptions();
        if(taskOptions != null && !taskOptions.isEmpty()){
            for(TaskOption taskOption : taskOptions){
                if(taskOption.getOption().equals(task.getStatement())){
                    errorList.add(new ErrorItem("option","As alternativas não podem ser iguais ao enunciado da atividade."));
                    return;
                }
            }
        }
    }
}
