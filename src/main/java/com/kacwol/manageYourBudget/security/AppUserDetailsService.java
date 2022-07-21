package com.kacwol.manageYourBudget.security;

import com.kacwol.manageYourBudget.user.model.User;
import com.kacwol.manageYourBudget.user.service.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public AppUserDetailsService(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User doesn't exist");
        });
        return new AppUserDetails(user);
    }
}
