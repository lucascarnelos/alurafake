package br.com.alura.AluraFake.infra.api.course;

import br.com.alura.AluraFake.core.gateway.CoursePersistenceGateway;
import br.com.alura.AluraFake.core.usecase.course.input.CreateCourseInput;
import br.com.alura.AluraFake.core.usecase.course.CreateCourseUseCase;
import br.com.alura.AluraFake.core.usecase.user.VerifyUserExistsUseCase;
import br.com.alura.AluraFake.infra.api.course.dto.CourseListItemDTO;
import br.com.alura.AluraFake.infra.api.course.dto.NewCourseDTO;
import br.com.alura.AluraFake.infra.mapper.CourseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CoursePersistenceGateway coursePersistenceGateway;
    private final VerifyUserExistsUseCase verifyUserExistsUseCase;
    private final CreateCourseUseCase createCourseUseCase;

    @Autowired
    public CourseController(CoursePersistenceGateway coursePersistenceGateway, VerifyUserExistsUseCase verifyUserExistsUseCase, CreateCourseUseCase createCourseUseCase){
        this.coursePersistenceGateway = coursePersistenceGateway;
        this.verifyUserExistsUseCase = verifyUserExistsUseCase;
        this.createCourseUseCase = createCourseUseCase;
    }

    @Transactional
    @PostMapping("/course/new")
    public ResponseEntity createCourse(@Valid @RequestBody NewCourseDTO newCourse) {
        createCourseUseCase.execute(new CreateCourseInput(CourseMapper.dtoToDomain(newCourse), newCourse.getEmailInstructor()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/course/all")
    public ResponseEntity<List<CourseListItemDTO>> createCourse() {
        List<CourseListItemDTO> courses = coursePersistenceGateway.findAll().stream()
                .map(CourseListItemDTO::new)
                .toList();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/course/{id}/publish")
    public ResponseEntity createCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

}
