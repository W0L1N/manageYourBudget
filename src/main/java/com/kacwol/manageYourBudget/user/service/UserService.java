package com.kacwol.manageYourBudget.user.service;

import com.kacwol.manageYourBudget.exception.UserNameAlreadyExistsException;
import com.kacwol.manageYourBudget.security.AppUserDetails;
import com.kacwol.manageYourBudget.security.AppUserDetailsService;
import com.kacwol.manageYourBudget.user.model.User;
import com.kacwol.manageYourBudget.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AppUserDetailsService userDetailsService;

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    @Autowired
    public UserService(AppUserDetailsService userDetailsService, UserRepo userRepo, UserMapper userMapper) {
        this.userDetailsService = userDetailsService;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public void register(UserDto userDto) {
        if (!userRepo.existsByUsername(userDto.getUsername())) {
            userRepo.save(userMapper.dtoToEntity(userDto, "ROLE_USER"));
        } else
            throw new UserNameAlreadyExistsException();
    }


    public User getByUserName(String name) {
        AppUserDetails details = (AppUserDetails) userDetailsService.loadUserByUsername(name);
        return details.getUser();
    }
}
