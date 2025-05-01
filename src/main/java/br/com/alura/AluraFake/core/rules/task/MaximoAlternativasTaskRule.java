package br.com.alura.AluraFake.core.rules.task;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.rules.ValidationRule;

import java.util.List;

public class MaximoAlternativasTaskRule implements ValidationRule<Task> {

    @Override
    public void execute(List<ErrorItem> errorList ,Task task) {
        var taskOptions = task.getOptions();
        if(task.isSingleChoice()){
            if(taskOptions == null || !(taskOptions.size() >= 2 && taskOptions.size() <= 5))
                errorList.add(new ErrorItem("options","A atividade deve ter no minimo 2 e no máximo 5 alternativas."));
        }
        if(task.isMultipleChoice()){
            if(taskOptions == null || !(taskOptions.size() >= 3 && taskOptions.size() <= 5))
                errorList.add(new ErrorItem("options","A atividade deve ter no minimo 3 e no máximo 5 alternativas."));
        }
    }
}
