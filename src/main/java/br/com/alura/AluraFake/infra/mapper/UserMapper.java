package br.com.alura.AluraFake.infra.mapper;

import br.com.alura.AluraFake.core.model.user.User;
import br.com.alura.AluraFake.infra.api.user.dto.NewUserDTO;
import br.com.alura.AluraFake.infra.persistence.user.UserEntity;

public class UserMapper {

    public static UserEntity domainToEntity(User user){
        return new UserEntity(
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getPassword()
                );
    }

    public static User entityToDomain(UserEntity user){
        return new User(
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getPassword()
        );
    }

    public static User dtoToDomain(NewUserDTO newUserDTO){
        return new User(
                newUserDTO.getName(),
                newUserDTO.getEmail(),
                newUserDTO.getRole(),
                newUserDTO.getPassword()
        );
    }

}
