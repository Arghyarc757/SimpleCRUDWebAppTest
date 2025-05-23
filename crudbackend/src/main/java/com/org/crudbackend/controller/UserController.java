package com.org.crudbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.crudbackend.exception.UserNotFoundException;
import com.org.crudbackend.model.User;
import com.org.crudbackend.repository.UserRepositary;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    
    @Autowired
    private UserRepositary userRepositary;

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userRepositary.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepositary.findAll();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepositary.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepositary.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepositary.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable Long id) {
        if(!userRepositary.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepositary.deleteById(id);
        return "User with id " + id + " has been deleted successfully";
    }
}
