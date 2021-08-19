package com.todo.todoapi.data.controller;

import com.todo.todoapi.data.config.JwtTokenUtil;
import com.todo.todoapi.data.model.ApiResponse;
import com.todo.todoapi.data.model.AuthToken;
import com.todo.todoapi.data.model.LoginDetails;
import com.todo.todoapi.data.model.User;
import com.todo.todoapi.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ApiResponse<AuthToken> authenticateUser(@RequestBody LoginDetails loginDetails) throws Exception {

        Iterable<User> users = userRepository.findUserByUserEmail(loginDetails.getUserEmail());
        if(users != null){
            User user = users.iterator().next();
            if(user.getUserPassword().equals(loginDetails.getUserPassword())){
                final String token = jwtTokenUtil.generateToken(user);
                return new ApiResponse<>(200, "Authentication successful", new AuthToken(token, user.getUserEmail()));
            }
        }
        return new ApiResponse<>(403, "Authentication failed", null);
    }
}
