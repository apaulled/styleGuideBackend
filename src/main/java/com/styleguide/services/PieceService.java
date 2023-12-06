package com.styleguide.services;

import com.styleguide.models.enums.ClothingType;
import com.styleguide.models.Piece;
import com.styleguide.models.dto.UserCloset;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PieceService {

    Piece getPiece(UUID id);

    String uploadPiece(MultipartFile file) throws IOException;

    String uploadPieceForUser(MultipartFile file, UUID userId, ClothingType type) throws IOException;

    String uploadPiecesForUser(List<MultipartFile> files, UUID userId, List<ClothingType> types) throws IOException;

    UserCloset getCloset(UUID userId);

    // byte[] getPieceImage(UUID uuid);

}
