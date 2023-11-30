package com.styleguide.controllers;

import com.styleguide.services.PieceService;
import com.styleguide.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/outfit")
public class OutfitController {

    private final UserService userService;
    private final PieceService pieceService;

    @Autowired
    public OutfitController(PieceService pieceService, UserService userService) {
        this.pieceService = pieceService;
        this.userService = userService;
    }
}
