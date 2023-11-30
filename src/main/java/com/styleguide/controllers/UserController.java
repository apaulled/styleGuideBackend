package com.styleguide.controllers;

import com.styleguide.RabbitDispatch;
import com.styleguide.models.User;
import com.styleguide.services.PieceService;
import com.styleguide.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    private final RabbitDispatch rabbitDispatch;
    private final PieceService pieceService;

    @Autowired
    public UserController(UserService userService, RabbitDispatch rabbitDispatch,
                          PieceService pieceService) {
        this.userService = userService;
        this.rabbitDispatch = rabbitDispatch;
        this.pieceService = pieceService;
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

    @PostMapping(value = "/{userId}/piece")
    public ResponseEntity<?> uploadPieceForUser(@PathVariable UUID userId,
                                                @RequestParam("image") MultipartFile file) throws IOException {
        String response = pieceService.uploadPieceForUser(file, userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
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
