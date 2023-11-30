package com.styleguide.services;

import com.styleguide.models.ClothingType;
import com.styleguide.models.Color;
import com.styleguide.models.Piece;
import com.styleguide.models.UserCloset;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface PieceService {

    Piece getPiece(UUID id);

    String uploadPiece(MultipartFile file) throws IOException;

    String uploadPieceForUser(MultipartFile file, UUID userId, ClothingType type) throws IOException;

    UserCloset getCloset(UUID userId);

    // byte[] getPieceImage(UUID uuid);

}
