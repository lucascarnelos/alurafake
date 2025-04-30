package br.com.alura.AluraFake.infra.persistence.task;

import br.com.alura.AluraFake.core.model.task.Type;
import br.com.alura.AluraFake.infra.persistence.course.CourseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;
    private String statement;
    @Column(name = "order_number")
    private Integer order;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "task")
    private List<TaskOptionEntity> options;


    public TaskEntity() {
    }

    public TaskEntity(Long id, LocalDateTime createdAt, Type type, CourseEntity course, String statement, Integer order, List<TaskOptionEntity> options) {
        this.id = id;
        this.createdAt = createdAt;
        this.type = type;
        this.course = course;
        this.statement = statement;
        this.order = order;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
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

    public List<TaskOptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<TaskOptionEntity> options) {
        this.options = options;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
