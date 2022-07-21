package com.kacwol.manageYourBudget.user.service;

import com.kacwol.manageYourBudget.user.model.User;
import com.kacwol.manageYourBudget.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class UserMapper {

    private final PasswordEncoder encoder;

    @Autowired
    public UserMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User dtoToEntity(UserDto dto, String... roles) {
        return User.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .roles(Set.of(roles))
                .build();
    }
}
