package br.com.alura.AluraFake.core.usecase.course.impl;

import br.com.alura.AluraFake.core.exception.CourseNotExistsException;
import br.com.alura.AluraFake.core.exception.InvalidCourseException;
import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.gateway.TaskPersistenceGateway;
import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.course.Status;
import br.com.alura.AluraFake.core.model.task.Task;
import br.com.alura.AluraFake.core.usecase.course.PublishCourseUseCase;
import br.com.alura.AluraFake.core.usecase.course.rules.TaskOfEachTypeRule;
import br.com.alura.AluraFake.core.usecase.course.rules.ValidationRule;

import java.util.List;

public class PublishCourseUseCaseImpl implements PublishCourseUseCase {

    private final CoursePersistenceGateway coursePersistenceGateway;
    private final TaskPersistenceGateway taskPersistenceGateway;

    public PublishCourseUseCaseImpl(CoursePersistenceGateway coursePersistenceGateway, TaskPersistenceGateway taskPersistenceGateway) {
        this.coursePersistenceGateway = coursePersistenceGateway;
        this.taskPersistenceGateway = taskPersistenceGateway;
    }

    @Override
    public void execute(Long courseId) {
        Course course = coursePersistenceGateway.findById(courseId);
        if(course == null){
            throw new CourseNotExistsException("");
        }
        if(!Status.BUILDING.equals(course.getStatus()))
            throw new InvalidCourseException("");

        List<Task> tasks = taskPersistenceGateway.findAllByCourseOrderByOrder(course);
        //Ter atividades com order em sequência contínua (ex: 1, 2, 3...). - ESSA PARTE EU ENTENDO QUE JÁ VAI ESTAR VALIDADO AO CRIAR CADA TASK, CONFORME IMPLEMENTADO NOS USECASES
        for(ValidationRule<List<Task>> validationRule : List.of(new TaskOfEachTypeRule())){
            validationRule.execute(tasks);
        }

        course.publish();
        coursePersistenceGateway.save(course);
    }
}
