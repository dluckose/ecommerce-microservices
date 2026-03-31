package com.ecommerce.userservice.auth.service;

import com.ecommerce.userservice.auth.repository.UserAuthenticationRepository;
//import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.auth.entity.UserAuth;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthenticationRepository authRepository;

    public CustomUserDetailsService(UserAuthenticationRepository repo) {
        this.authRepository = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserAuth user = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        /*return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();*/
        return UserPrincipal.build(user);
    }
}