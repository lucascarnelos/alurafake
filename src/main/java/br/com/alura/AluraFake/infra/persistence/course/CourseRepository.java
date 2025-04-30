package br.com.alura.AluraFake.infra.persistence.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long>{

}
