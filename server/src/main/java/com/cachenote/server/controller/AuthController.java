package com.cachenote.server.controller;


import com.cachenote.server.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController {


    @GetMapping("/hello-world")
    public User helloWorld() {
        return new User(1, "hello");
//        return "Hello World";
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> students = new ArrayList<>();
        students.add(new User(1, "1st"));
        students.add(new User(2, "2nd"));
        return students;
    }


}
