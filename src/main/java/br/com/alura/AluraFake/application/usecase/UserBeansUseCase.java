package br.com.alura.AluraFake.application.usecase;

import br.com.alura.AluraFake.core.gateway.UserPersistenceGateway;
import br.com.alura.AluraFake.core.usecase.user.CreateNewStudentUseCase;
import br.com.alura.AluraFake.core.usecase.user.VerifyUserExistsUseCase;
import br.com.alura.AluraFake.core.usecase.user.impl.CreateNewStudentUseCaseImpl;
import br.com.alura.AluraFake.core.usecase.user.impl.VerifyUserExistsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeansUseCase {

    private final UserPersistenceGateway userPersistenceGateway;

    public UserBeansUseCase(UserPersistenceGateway userPersistenceGateway) {
        this.userPersistenceGateway = userPersistenceGateway;
    }

    @Bean
    public CreateNewStudentUseCase createNewStudentUseCase(){
        return new CreateNewStudentUseCaseImpl(userPersistenceGateway);
    }

    @Bean
    public VerifyUserExistsUseCase verifyUserExistsUseCase(){
        return new VerifyUserExistsUseCaseImpl(userPersistenceGateway);
    }

}
