package com.styleguide.services;

import com.styleguide.models.Piece;
import com.styleguide.models.User;
import com.styleguide.models.dto.OutfitBoard;
import com.styleguide.models.dto.UserCloset;
import com.styleguide.models.dto.UserResponse;
import com.styleguide.models.enums.ClothingType;
import com.styleguide.repositories.OutfitRepository;
import com.styleguide.repositories.PieceRepository;
import com.styleguide.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PieceRepository pieceRepository;
    private final OutfitRepository outfitRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PieceRepository pieceRepository,
                           OutfitRepository outfitRepository) {
        this.userRepository = userRepository;
        this.pieceRepository = pieceRepository;
        this.outfitRepository = outfitRepository;
    }

    @Override
    public UserResponse getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        UserCloset closet = new UserCloset(
                id,
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.HEAD_WEAR).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.TOP).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.BOTTOM).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.SHOE).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.OUTER_WEAR).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.ACCESSORY).stream().map(Piece::toDto).toList());
        OutfitBoard board = new OutfitBoard(id, outfitRepository.findAllByUser(user));
        return new UserResponse(id, user.getName(), user.getCreatedAt(), closet, board);
    }

    @Override
    public OutfitBoard getOutfitsForUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        return new OutfitBoard(id, outfitRepository.findAllByUser(user));
    }

    @Override
    public UserCloset getUserCloset(UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        return new UserCloset(
                id,
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.HEAD_WEAR).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.TOP).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.BOTTOM).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.SHOE).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.OUTER_WEAR).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.ACCESSORY).stream().map(Piece::toDto).toList());
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse login(String username, String password) {
        Optional<User> userOptional = userRepository.findByNameAndPassword(username, password);
        if (userOptional.isEmpty()) return null;
        User user = userOptional.get();
        UserCloset closet = new UserCloset(
                user.getId(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.HEAD_WEAR).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.TOP).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.BOTTOM).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.SHOE).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.OUTER_WEAR).stream().map(Piece::toDto).toList(),
                pieceRepository.findAllByUserAndClothingType(user, ClothingType.ACCESSORY).stream().map(Piece::toDto).toList());
        OutfitBoard board = new OutfitBoard(user.getId(), outfitRepository.findAllByUser(user));
        return new UserResponse(user.getId(), user.getName(), user.getCreatedAt(), closet, board);
    }

    @Override
    public UserResponse register(String username, String password) {
        User user = new User(username, password);
        userRepository.save(user);
        return new UserResponse(user.getId(), user.getName(), user.getCreatedAt(), null, null);
    }
}
