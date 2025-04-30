package br.com.alura.AluraFake.infra.persistence.course;

import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.infra.persistence.user.UserEntity;
import jakarta.persistence.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Entity
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String title;
    private String description;
    @ManyToOne
    private UserEntity instructor;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime publishedAt;

    @Deprecated
    public CourseEntity(){}

    public CourseEntity(String title, String description, UserEntity instructor) {
        Assert.isTrue(instructor.isInstructor(), "Usuario deve ser um instrutor");
        this.title = title;
        this.instructor = instructor;
        this.description = description;
        this.status = Status.BUILDING;
    }

    public CourseEntity(Long id, LocalDateTime createdAt, String title, String description, UserEntity instructor, Status status, LocalDateTime publishedAt) {
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

    public UserEntity getInstructor() {
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
