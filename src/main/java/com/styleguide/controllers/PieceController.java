package com.styleguide.controllers;

import com.styleguide.models.Piece;
import com.styleguide.services.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/piece")
public class PieceController {

    private final PieceService pieceService;

    @Autowired
    public PieceController(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> uploadPiece(@RequestParam("image") MultipartFile file) throws IOException {
        String response = pieceService.uploadPiece(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping(value = "{uuid}")
    public ResponseEntity<?>  getPieceById(@PathVariable("uuid") UUID uuid){
        Piece piece = pieceService.getPiece(uuid);

        return ResponseEntity.status(HttpStatus.OK)
                .body(piece);
    }

    @GetMapping("/image/{uuid}")
    public ResponseEntity<?>  getImageById(@PathVariable("uuid") UUID uuid){
        byte[] image = pieceService.getPieceImage(uuid);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

}
