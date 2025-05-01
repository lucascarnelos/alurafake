package br.com.alura.AluraFake.core.rules.course;

import br.com.alura.AluraFake.core.exception.ErrorItem;
import br.com.alura.AluraFake.core.exception.InvalidCourseException;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.core.rules.ValidationRule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskOfEachTypeRule implements ValidationRule<List<Task>> {

    @Override
    public void execute(List<ErrorItem> errorList, List<Task> tasks) {
        Set<Type> types = new HashSet<>();
        for(Task task : tasks){
            if(task.getType() == null || types.contains(task.getType())){
                continue;
            }
            types.add(task.getType());
            if(types.contains(Type.OPEN_TEXT) && types.contains(Type.SINGLE_CHOICE) && types.contains(Type.MULTIPLE_CHOICE)) {
                return;
            }
        }
        throw new InvalidCourseException("Não é possível public um curso se não houver ao menos os 3 tipos de atividades: Open Text, Single Choice e Multiple Choice");
    }
}
