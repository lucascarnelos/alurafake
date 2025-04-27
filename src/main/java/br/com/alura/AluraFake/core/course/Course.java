package br.com.alura.AluraFake.core.course;

import br.com.alura.AluraFake.core.user.User;

import java.time.LocalDateTime;

public class Course {

    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String title;
    private String description;
    private User instructor;
    private Status status;
    private LocalDateTime publishedAt;

    public Course(){}

    public Course(String title, String description, User instructor) {
        if(instructor.isInstructor()){
            throw new RuntimeException("Usuario deve ser um instrutor");
        }
        this.title = title;
        this.instructor = instructor;
        this.description = description;
        this.status = Status.BUILDING;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getInstructor() {
        return instructor;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
}
