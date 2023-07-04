package com.kacwol.manageYourBudget.user.service;

import com.kacwol.manageYourBudget.exception.UserNameAlreadyExistsException;
import com.kacwol.manageYourBudget.user.model.User;
import com.kacwol.manageYourBudget.user.model.UserDto;
import com.kacwol.manageYourBudget.user.service.security.AppUserDetails;
import com.kacwol.manageYourBudget.user.service.security.AppUserDetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthService {

    private final AppUserDetailsService userDetailsService;

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    public void register(UserDto userDto) {
        if (!userRepo.existsByUsername(userDto.getUsername())) {
            userRepo.save(userMapper.dtoToEntity(userDto, "ROLE_USER"));
        } else {
            throw new UserNameAlreadyExistsException("User already exits.");
        }
    }

    public User getAuthenticatedUser() {
        final String name = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUserDetails details = (AppUserDetails) userDetailsService.loadUserByUsername(name);
        return details.getUser();
    }

    public Long getId() {
        return getAuthenticatedUser().getId();
    }
}
