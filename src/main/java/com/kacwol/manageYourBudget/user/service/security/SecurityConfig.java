package com.kacwol.manageYourBudget.user.service.security;

import com.kacwol.manageYourBudget.user.model.User;
import com.kacwol.manageYourBudget.user.service.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

@Configuration
public class SecurityConfig {

    private final UserRepo userRepo;

    @Autowired
    public SecurityConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AppUserDetailsService appUserDetailsService() {
        AppUserDetailsService userDetailsService = new AppUserDetailsService(userRepo, getBcryptPasswordEncoder());
        userRepo.save(new User(1L, "admin", getBcryptPasswordEncoder().encode("admin"), Set.of("ROLE_USER", "ROLE_ADMIN")));
        return  userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((autz) -> {
                    try {
                        autz
                                .requestMatchers("/h2-console").hasRole("ADMIN")
                                .requestMatchers("/register").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .httpBasic()
                                .and()
                                .csrf()
                                .disable();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout()
                .logoutSuccessUrl("/bye").permitAll();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public AuthenticationProvider setProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService());
        provider.setPasswordEncoder(getBcryptPasswordEncoder());
        return provider;
    }

}