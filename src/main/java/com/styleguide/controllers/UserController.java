package com.styleguide.controllers;

import com.styleguide.RabbitDispatch;
import com.styleguide.models.User;
import com.styleguide.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final RabbitDispatch rabbitDispatch;

    @Autowired
    public UserController(UserService userService, RabbitDispatch rabbitDispatch) {
        this.userService = userService;
        this.rabbitDispatch = rabbitDispatch;
    }

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getById(@PathVariable UUID userId) {
        User user = userService.getUser(userId);
        System.out.println(user);
        return user;
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public String test() {
        rabbitDispatch.dispatchTest();
        return "uhm";
    }
}
