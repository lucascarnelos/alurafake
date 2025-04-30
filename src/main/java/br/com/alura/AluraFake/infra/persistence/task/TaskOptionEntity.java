package br.com.alura.AluraFake.infra.persistence.task;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "TaskOption")
public class TaskOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;
    @Column(name = "option_string")
    private String option;
    private Boolean isCorrect;

    public TaskOptionEntity() {
    }

    public TaskOptionEntity(Long id, LocalDateTime createdAt, TaskEntity task, String option, Boolean isCorrect) {
        this.id = id;
        this.createdAt = createdAt;
        this.task = task;
        this.option = option;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
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
}
