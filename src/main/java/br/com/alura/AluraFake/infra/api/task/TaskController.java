package br.com.alura.AluraFake.infra.api.task;

import br.com.alura.AluraFake.core.usecase.task.CreateOpenTextTaskUseCase;
import br.com.alura.AluraFake.core.usecase.task.input.CreateTaskInput;
import br.com.alura.AluraFake.infra.api.task.dto.NewTaskDTO;
import br.com.alura.AluraFake.infra.mapper.TaskMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private final CreateOpenTextTaskUseCase createOpenTextTaskUseCase;

    public TaskController(CreateOpenTextTaskUseCase createOpenTextTaskUseCase) {
        this.createOpenTextTaskUseCase = createOpenTextTaskUseCase;
    }

    @PostMapping("/task/new/opentext")
    public ResponseEntity newOpenTextExercise(@RequestBody @Valid NewTaskDTO newTaskDTO) {
        createOpenTextTaskUseCase.execute(newTaskDTO.createTaskInput());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/task/new/singlechoice")
    public ResponseEntity newSingleChoice() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/task/new/multiplechoice")
    public ResponseEntity newMultipleChoice() {
        return ResponseEntity.ok().build();
    }

}