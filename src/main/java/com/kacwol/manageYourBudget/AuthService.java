package com.kacwol.manageYourBudget;

import com.kacwol.manageYourBudget.user.service.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo userRepo;

    @Autowired
    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Long getId(Authentication auth) {
        return userRepo.findByUsername(auth.getName()).orElseThrow().getId();
    }

}
