package br.com.alura.AluraFake.core.model.task;

import java.time.LocalDateTime;

public class TaskOption {

    private Long id;
    private LocalDateTime createdAt;
    private Task task;
    private String option;
    private Boolean isCorrect;

    public TaskOption() {
    }

    public TaskOption(Long id, LocalDateTime createdAt, Task task, String option, Boolean isCorrect) {
        this.id = id;
        this.createdAt = createdAt;
        this.task = task;
        this.option = option;
        this.isCorrect = isCorrect;
    }

    public static TaskOption Create(Task task, String option, Boolean isCorrect){
        return new TaskOption(
                null,
                LocalDateTime.now(),
                task,
                option,
                isCorrect
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
