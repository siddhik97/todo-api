package com.todo.todoapi.data.controller;

import com.todo.todoapi.data.model.ApiResponse;
import com.todo.todoapi.data.model.User;
import com.todo.todoapi.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ApiResponse<Iterable<User>> getUsers() {
        //go to repo and fetch all users
        return new ApiResponse<>(200, "User list fetched successfully", userRepository.findAll());
    }

    @PostMapping("/user")
    public ApiResponse<User> createUser(@RequestBody User user) {
        //go to repo(User) and use save method to insert in DB
        Iterable<User> users = userRepository.findUserByUserEmail(user.getUserEmail());
        if(!users.iterator().hasNext()){
            userRepository.save(user);
            return new ApiResponse<>(200, "User added successfully", user);
        } else {
            return new ApiResponse<>(409, "Email-id already exists", null);
        }
    }
}
