package br.com.alura.AluraFake.core.model.course;

import br.com.alura.AluraFake.core.model.user.User;

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

    public static Course Create(String title, String description, User instructor) {
        if(instructor.isInstructor()){
            throw new RuntimeException("Usuario deve ser um instrutor");
        }
        return new Course(
              null,
              null,
              title,
              description,
              instructor,
              Status.BUILDING,
              null
        );
    }

    public Course(Long id, LocalDateTime createdAt, String title, String description, User instructor, Status status, LocalDateTime publishedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.status = status;
        this.publishedAt = publishedAt;
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
