package com.styleguide.services;

import com.styleguide.ImageUtil;
import com.styleguide.models.Piece;
import com.styleguide.repositories.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class PieceServiceImpl implements PieceService {

    private final PieceRepository pieceRepository;

    @Autowired
    public PieceServiceImpl(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    public String uploadPiece(MultipartFile file) throws IOException {
        byte[] stuff = ImageUtil.compressImage(file.getBytes());
        System.out.println(stuff);
        System.out.println(Arrays.toString(stuff));
        Piece piece = new Piece(file.getOriginalFilename(), stuff);

        pieceRepository.save(piece);

        return "YUH!!";
    }

    public Piece getPiece(UUID id) {
        Piece piece = pieceRepository.findById(id).orElseThrow();

        return Piece.builder()
                .name(piece.getName())
                .imageData(ImageUtil.decompressImage(piece.getImageData()))
                .primaryColor(piece.getPrimaryColor())
                .secondaryColor(piece.getSecondaryColor())
                .createdAt(piece.getCreatedAt()).build();
    }

    @Transactional
    public byte[] getPieceImage(UUID uuid) {
        Piece piece = pieceRepository.findById(uuid).orElseThrow();
        return ImageUtil.decompressImage(piece.getImageData());
    }
}
