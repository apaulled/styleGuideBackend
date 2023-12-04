package com.styleguide.controllers;

import com.styleguide.models.dto.OutfitBoard;
import com.styleguide.models.dto.UserCloset;
import com.styleguide.models.dto.UserResponse;
import com.styleguide.services.RabbitDispatch;
import com.styleguide.models.enums.ClothingType;
import com.styleguide.models.enums.Color;
import com.styleguide.models.User;
import com.styleguide.services.OutfitService;
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
    private final OutfitService outfitService;

    @Autowired
    public UserController(UserService userService, RabbitDispatch rabbitDispatch,
                          PieceService pieceService, OutfitService outfitService) {
        this.userService = userService;
        this.rabbitDispatch = rabbitDispatch;
        this.pieceService = pieceService;
        this.outfitService = outfitService;
    }

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserResponse getById(@PathVariable UUID userId) {
        return userService.getUser(userId);
    }

    @GetMapping(value = "/{userId}/closet", produces = APPLICATION_JSON_VALUE)
    public UserCloset getUserCloset(@PathVariable UUID userId) {
        return userService.getUserCloset(userId);
    }

    @GetMapping(value = "{userId}/outfits", produces = APPLICATION_JSON_VALUE)
    public OutfitBoard getUserOutfits(@PathVariable UUID userId) {
        return userService.getOutfitsForUser(userId);
    }

    @PostMapping(value = "/{userId}/piece")
    public ResponseEntity<?> uploadPieceForUser(@PathVariable UUID userId,
                                                @RequestParam("image") MultipartFile file,
                                                @RequestParam("clothingType") ClothingType type) throws IOException {
        String response = pieceService.uploadPieceForUser(file, userId, type);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(value = "/{userId}/color-outfit", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String requestColorOutfit(@PathVariable UUID userId,
                                 @RequestParam Color color) {
        outfitService.requestColorOutfit(userId, color);
        return "mmmmm outfits";
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public String test() {
        rabbitDispatch.dispatchTest();
        return "uhm";
    }

}
