package br.com.alura.AluraFake.infra.api.task.dto;

import br.com.alura.AluraFake.core.usecase.task.input.CreateTaskInput;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.LinkedList;
import java.util.List;

public record NewTaskDTO(
        @NotNull
        Long courseId,
        @NotNull @Length(min = 4, max = 255)
        String statement,
        @NotNull
        Integer order,
        List<NewTaskOptionDTO> options

) {

    public record NewTaskOptionDTO(
            String option,
            Boolean isCorrect
    ){

    }

    public CreateTaskInput createTaskInput(){
        return new CreateTaskInput(
                this.courseId(),
                this.statement(),
                this.order(),
                mapOptionsToInput()
        );
    }

    private List<CreateTaskInput.CreateTaskOption> mapOptionsToInput(){
        if(options == null || options.isEmpty())
            return new LinkedList<>();
        return this.options.stream().map(taskOption ->
                new CreateTaskInput.CreateTaskOption(
                taskOption.option,
                taskOption.isCorrect
                )
        ).toList();
    }

}
