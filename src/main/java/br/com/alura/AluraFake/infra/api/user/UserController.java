package br.com.alura.AluraFake.infra.api.user;

import br.com.alura.AluraFake.core.gateway.UserPersistenceGateway;
import br.com.alura.AluraFake.core.usecase.user.CreateNewStudentUseCase;
import br.com.alura.AluraFake.infra.api.user.dto.NewUserDTO;
import br.com.alura.AluraFake.infra.api.user.dto.UserListItemDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserPersistenceGateway userPersistenceGateway;
    private final CreateNewStudentUseCase createNewStudentUseCase;

    public UserController(UserPersistenceGateway userPersistenceGateway, CreateNewStudentUseCase createNewStudentUseCase) {
        this.userPersistenceGateway = userPersistenceGateway;
        this.createNewStudentUseCase = createNewStudentUseCase;
    }

    @Transactional
    @PostMapping("/user/new")
    public ResponseEntity<?> newStudent(@RequestBody @Valid NewUserDTO newUser) {
        createNewStudentUseCase.execute(newUser.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user/all")
    public List<UserListItemDTO> listAllUsers() {
        return userPersistenceGateway.findAll().stream().map(UserListItemDTO::new).toList();
    }

}
