package br.com.alura.AluraFake.core.model.task;

import br.com.alura.AluraFake.core.model.course.Course;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {

    private Long id;
    private LocalDateTime createdAt;
    private Type type;
    private Course course;
    private String statement;
    private Integer order;
    private List<TaskOption> options;

    public Task(){}

    public Task(Long id, LocalDateTime createdAt, Type type, Course course, String statement, Integer order, List<TaskOption> options) {
        this.id = id;
        this.createdAt = createdAt;
        this.type = type;
        this.course = course;
        this.statement = statement;
        this.order = order;
        this.options = options;
    }

    public static Task Create(Type type, Course course, String statement, Integer order, List<TaskOption> options){
        return new Task(
                null,
                LocalDateTime.now(),
                type,
                course,
                statement,
                order,
                options
        );
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<TaskOption> getOptions() {
        return options;
    }

    public void setOptions(List<TaskOption> options) {
        this.options = options;
    }

}
