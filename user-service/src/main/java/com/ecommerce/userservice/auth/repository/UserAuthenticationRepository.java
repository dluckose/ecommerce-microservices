package com.ecommerce.userservice.auth.repository;

//import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.auth.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserAuthenticationRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByEmail(String email);

}