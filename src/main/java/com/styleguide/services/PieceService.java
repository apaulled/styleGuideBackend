package com.styleguide.services;

import com.styleguide.models.Piece;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface PieceService {

    Piece getPiece(UUID id);

    String uploadPiece(MultipartFile file) throws IOException;

    byte[] getPieceImage(UUID uuid);

}
