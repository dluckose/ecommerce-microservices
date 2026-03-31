package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.auth.service.UserPrincipal;
import com.ecommerce.userservice.dto.UpdateUserProfileDto;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping ("/update")
    public User updateUser(@RequestBody UpdateUserProfileDto userProfileDto) {
        return userService.updateUser(userProfileDto);
    }

   @GetMapping("/user")
    public User getUser() {
        return userService.getUserById();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

   /* @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.ok("User deleted successfully");
    }*/
   @DeleteMapping("/delete-me")
   public ResponseEntity<?> deleteUser(Authentication authentication) {
       // Cast the principal to our custom class
       UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

       // Use the ID directly
       userService.deleteUser(user.getId());

       return ResponseEntity.ok("User deleted successfully.");
   }
}