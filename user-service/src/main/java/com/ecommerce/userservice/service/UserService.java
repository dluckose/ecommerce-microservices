package com.ecommerce.userservice.service;

import com.ecommerce.userservice.config.UserContext;
import com.ecommerce.userservice.dto.UpdateUserProfileDto;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.UserRepository;
import com.ecommerce.userservice.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.email());
        user.setName(userDto.name());
        user.setPhone(userDto.phone());
        user.setPassword(userDto.password());
        user.setRole(userDto.role());
        return userRepository.save(user);
    }

   public User getUserById() {
       Long myId = UserContext.getUserId();
       return userRepository.findById(myId)
               .orElseThrow(() -> new UserNotFoundException("User not found"));
   }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User updateUser(UpdateUserProfileDto userProfileDto) {
        Long myId = UserContext.getUserId();
        User user = userRepository.findById(myId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userProfileDto.name() != null) {
            user.setName(userProfileDto.name());
        }
        if (userProfileDto.phone() != null) {
            user.setPhone(userProfileDto.phone());
        }
        return userRepository.save(user);
     }
    /*public void deleteUser() {
        Long userId = UserContext.getUserId();
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }*/
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

}



