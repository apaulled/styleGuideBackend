package com.styleguide.controllers;

import com.styleguide.RabbitDispatch;
import com.styleguide.models.User;
import com.styleguide.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/{userId}/clothing", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addNewClothingPiece(@PathVariable UUID userId) {
        throw new RuntimeException("not implemented"); // TODO new clothing
    }

    @GetMapping(value = "/{userId}/outfit", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getOutfit(@PathVariable UUID userId) {
        throw new RuntimeException("not implemented"); // TODO get outfits
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public String test() {
        rabbitDispatch.dispatchTest();
        return "uhm";
    }
}
