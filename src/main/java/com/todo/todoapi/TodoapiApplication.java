package com.todo.todoapi;

import com.todo.todoapi.data.model.User;
import com.todo.todoapi.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TodoapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoapiApplication.class, args);
    }

}
