package br.com.alura.AluraFake.infra.gateway;

import br.com.alura.AluraFake.core.gateway.UserPersistenceGateway;
import br.com.alura.AluraFake.core.model.user.User;
import br.com.alura.AluraFake.infra.mapper.UserMapper;
import br.com.alura.AluraFake.infra.persistence.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryPersistenceGateway implements UserPersistenceGateway {

    private final UserRepository userRepository;

    public UserRepositoryPersistenceGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        var userEntity = UserMapper.domainToEntity(user);
        return UserMapper.entityToDomain(userRepository.save(userEntity));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::entityToDomain);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().map(UserMapper::entityToDomain).toList();
    }

}
