package br.com.alura.AluraFake.infra;

import br.com.alura.AluraFake.core.model.course.Course;
import br.com.alura.AluraFake.core.model.user.Role;
import br.com.alura.AluraFake.core.model.user.User;
import br.com.alura.AluraFake.infra.persistence.course.CourseEntity;
import br.com.alura.AluraFake.infra.persistence.course.CourseRepository;
import br.com.alura.AluraFake.infra.persistence.user.UserEntity;
import br.com.alura.AluraFake.infra.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public DataSeeder(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) {
        if (!"dev".equals(activeProfile)) return;

        if (userRepository.count() == 0) {
            UserEntity caio = new UserEntity("Caio", "caio@alura.com.br", Role.STUDENT);
            UserEntity paulo = new UserEntity("Paulo", "paulo@alura.com.br", Role.INSTRUCTOR);
            userRepository.saveAll(Arrays.asList(caio, paulo));
            courseRepository.save(new CourseEntity("Java", "Aprenda Java com Alura", paulo));
        }
    }
}