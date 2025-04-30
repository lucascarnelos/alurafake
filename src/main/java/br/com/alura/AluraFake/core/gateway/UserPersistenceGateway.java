package br.com.alura.AluraFake.core.gateway;

import br.com.alura.AluraFake.core.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistenceGateway {

    User save(User user);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findAll();
}
