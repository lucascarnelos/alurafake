package br.com.alura.AluraFake.core.rules.task;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.TaskOption;
import br.com.alura.AluraFake.core.rules.ValidationRule;

import java.util.List;

public class AlternativaTrueUnicaRule implements ValidationRule<Task> {

    @Override
    public void execute(List<ErrorItem> errorList, Task task) {
        var taskOptions = task.getOptions();
        int correctOptions = 0;
        int incorrectOptions = 0;
        if(taskOptions != null && !taskOptions.isEmpty()){
            for(TaskOption taskOption : taskOptions){
                if(taskOption.getCorrect())
                    correctOptions++;
                if(!taskOption.getCorrect())
                    incorrectOptions++;
            }

        }

        if(task.isSingleChoice())
            if(correctOptions > 1)
                errorList.add(new ErrorItem("isCorrect","A atividade deve ter uma única alternativa correta."));

        if(task.isMultipleChoice()){
            if(correctOptions < 2)
                errorList.add(new ErrorItem("isCorrect","A atividade deve ter 2 ou mais alternativas corretas."));
            if(incorrectOptions < 1)
                errorList.add(new ErrorItem("isCorrect","A atividade deve ter ao menos uma alternativa  incorreta."));
        }
    }

}
