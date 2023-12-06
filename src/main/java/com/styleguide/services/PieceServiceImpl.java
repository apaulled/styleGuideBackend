package com.styleguide.services;

import com.styleguide.models.*;
import com.styleguide.models.dto.UserCloset;
import com.styleguide.models.enums.ClothingType;
import com.styleguide.repositories.PieceRepository;
import com.styleguide.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class PieceServiceImpl implements PieceService {

    private final PieceRepository pieceRepository;
    private final RabbitDispatch rabbitDispatch;
    private final UserRepository userRepository;
    private static final String imageUrl = "/Users/paulgagliano/Desktop/styleguide/";

    @Autowired
    public PieceServiceImpl(PieceRepository pieceRepository, RabbitDispatch rabbitDispatch,
                            UserRepository userRepository) {
        this.pieceRepository = pieceRepository;
        this.rabbitDispatch = rabbitDispatch;
        this.userRepository = userRepository;
    }

    public String uploadPiece(MultipartFile file) throws IOException {
        //byte[] stuff = ImageUtil.compressImage(file.getBytes());
        Piece piece = new Piece(file.getOriginalFilename());
        pieceRepository.save(piece);

        // I know this is bad lol, doing it for time purposes
        File realFile = new File(imageUrl + piece.getId().toString() + ".png");

        try (OutputStream os = new FileOutputStream(realFile)) {
            os.write(file.getBytes());
        }

        piece.setUrl(imageUrl + piece.getId().toString() + ".png");
        pieceRepository.save(piece);

        rabbitDispatch.sendImageToProcessor(piece);

        return "YUH!!";
    }

    public String uploadPieceForUser(MultipartFile file, UUID userId, ClothingType type) throws IOException {
        //byte[] stuff = ImageUtil.compressImage(file.getBytes());
        Piece piece = new Piece(file.getOriginalFilename());
        piece.setClothingType(type);

        User user = userRepository.findById(userId).orElseThrow();
        piece.setUser(user);
        pieceRepository.save(piece);

        // I know this is bad lol, doing it for time purposes
        File realFile = new File(imageUrl + piece.getId().toString() + ".png");

        try (OutputStream os = new FileOutputStream(realFile)) {
            os.write(file.getBytes());
        }

        piece.setUrl(imageUrl + piece.getId().toString() + ".png");
        pieceRepository.save(piece);

        rabbitDispatch.sendImageToProcessor(piece);

        return "YUH!!";
    }

    @Override
    public String uploadPiecesForUser(List<MultipartFile> files, UUID userId, List<ClothingType> types) throws IOException {
        for (int i = 0; i < files.size(); i++) {
            uploadPieceForUser(files.get(i), userId, types.get(i));
        }
        return "YUHHHHHHH";
    }

    public Piece getPiece(UUID id) {
        Piece piece = pieceRepository.findById(id).orElseThrow();

        return Piece.builder()
                .name(piece.getName())
                .primaryColor(piece.getPrimaryColor())
                .secondaryColor(piece.getSecondaryColor())
                .createdAt(piece.getCreatedAt()).build();
    }

    public UserCloset getCloset(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return new UserCloset(
                userId,
                pieceRepository.findAllByUserAndClothingType(
                        user, ClothingType.HEAD_WEAR)
                        .stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(
                        user, ClothingType.TOP)
                        .stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(
                                user, ClothingType.BOTTOM)
                        .stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(
                                user, ClothingType.SHOE)
                        .stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(
                                user, ClothingType.OUTER_WEAR)
                        .stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(
                                user, ClothingType.ACCESSORY)
                        .stream().map(Piece::toDto).toList()
                );
    }

    /*@Transactional
    public byte[] getPieceImage(UUID uuid) {
        Piece piece = pieceRepository.findById(uuid).orElseThrow();
        return ImageUtil.decompressImage(piece.getImageData());
    }*/
}
