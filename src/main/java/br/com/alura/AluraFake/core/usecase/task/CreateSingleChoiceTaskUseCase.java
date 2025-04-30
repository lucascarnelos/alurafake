package br.com.alura.AluraFake.core.usecase.task;

import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.usecase.InOutUseCase;
import br.com.alura.AluraFake.core.usecase.task.input.CreateTaskInput;

public interface CreateSingleChoiceTaskUseCase extends InOutUseCase<CreateTaskInput, Task> {

}
