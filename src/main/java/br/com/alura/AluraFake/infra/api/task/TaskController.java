package br.com.alura.AluraFake.infra.api.task;

import br.com.alura.AluraFake.core.usecase.task.CreateMultipleChoiceTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.CreateOpenTextTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.CreateSingleChoiceTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.input.CreateTaskInput;
import br.com.alura.AluraFake.infra.api.task.dto.NewTaskDTO;
import br.com.alura.AluraFake.infra.mapper.TaskMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private final CreateOpenTextTaskUseCase createOpenTextTaskUseCase;
    private final CreateSingleChoiceTaskUseCase createSingleChoiceTaskUseCase;
    private final CreateMultipleChoiceTaskUseCase multipleChoiceTaskUseCase;

    public TaskController(CreateOpenTextTaskUseCase createOpenTextTaskUseCase, CreateSingleChoiceTaskUseCase createSingleChoiceTaskUseCase, CreateMultipleChoiceTaskUseCase multipleChoiceTaskUseCase) {
        this.createOpenTextTaskUseCase = createOpenTextTaskUseCase;
        this.createSingleChoiceTaskUseCase = createSingleChoiceTaskUseCase;
        this.multipleChoiceTaskUseCase = multipleChoiceTaskUseCase;
    }

    @PostMapping("/task/new/opentext")
    public ResponseEntity newOpenTextExercise(@RequestBody @Valid NewTaskDTO newTaskDTO) {
        createOpenTextTaskUseCase.execute(newTaskDTO.createTaskInput());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/task/new/singlechoice")
    public ResponseEntity newSingleChoice(@RequestBody @Valid NewTaskDTO newTaskDTO) {
        createSingleChoiceTaskUseCase.execute(newTaskDTO.createTaskInput());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/task/new/multiplechoice")
    public ResponseEntity newMultipleChoice(@RequestBody @Valid NewTaskDTO newTaskDTO) {
        multipleChoiceTaskUseCase.execute(newTaskDTO.createTaskInput());
        return ResponseEntity.ok().build();
    }

}