package br.com.alura.AluraFake.core.usecase.user.impl;

import br.com.alura.AluraFake.core.exception.UserNotInstructorException;
import br.com.alura.AluraFake.core.gateway.UserPersistenceGateway;
import br.com.alura.AluraFake.core.model.user.User;
import br.com.alura.AluraFake.core.usecase.user.VerifyUserExistsUseCase;

import java.util.Optional;

public class VerifyUserExistsUseCaseImpl implements VerifyUserExistsUseCase {

    private final UserPersistenceGateway userPersistenceGateway;

    public VerifyUserExistsUseCaseImpl(UserPersistenceGateway userPersistenceGateway) {
        this.userPersistenceGateway = userPersistenceGateway;
    }

    @Override
    public User execute(String email) {
        Optional<User> possibleAuthor = userPersistenceGateway
                .findByEmail(email)
                .filter(User::isInstructor);
        //Caso implemente o bonus, pegue o instrutor logado
        if(possibleAuthor.isEmpty()) {
            throw new UserNotInstructorException("");
        }
        return possibleAuthor.get();
    }

}
