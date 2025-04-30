package br.com.alura.AluraFake.core.gateway;

import br.com.alura.AluraFake.core.model.task.Task;

public interface TaskPersistenceGateway {

    Task save(Task task);

}
