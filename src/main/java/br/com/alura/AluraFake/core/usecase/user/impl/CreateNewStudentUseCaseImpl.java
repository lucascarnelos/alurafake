package br.com.alura.AluraFake.core.usecase.user.impl;

import br.com.alura.AluraFake.core.gateway.UserPersistenceGateway;
import br.com.alura.AluraFake.core.model.user.User;
import br.com.alura.AluraFake.core.exception.EmailAlreadyExistsException;
import br.com.alura.AluraFake.core.usecase.user.CreateNewStudentUseCase;

public class CreateNewStudentUseCaseImpl implements CreateNewStudentUseCase {
    private final UserPersistenceGateway userPersistenceGateway;

    public CreateNewStudentUseCaseImpl(UserPersistenceGateway userPersistenceGateway) {
        this.userPersistenceGateway = userPersistenceGateway;
    }

    @Override
    public User execute(User user) {
        if(userPersistenceGateway.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        return userPersistenceGateway.save(user);
    }
}
