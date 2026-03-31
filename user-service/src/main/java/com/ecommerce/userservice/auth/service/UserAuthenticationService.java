package com.ecommerce.userservice.auth.service;

import com.ecommerce.userservice.auth.dto.UserRegistrationRequestDto;
import com.ecommerce.userservice.auth.repository.UserAuthenticationRepository;
//import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.auth.entity.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    @Autowired
    private UserAuthenticationRepository authRepository;

    public UserAuth createUser(UserRegistrationRequestDto userRegDto, PasswordEncoder encoder) {
        UserAuth user = new UserAuth();
        user.setEmail(userRegDto.email());
        user.setName(userRegDto.name());
        user.setPhone(userRegDto.phone());
        user.setPassword(encoder.encode(userRegDto.password()));
        user.setRole("USER");
        return authRepository.save(user);
    }
}



