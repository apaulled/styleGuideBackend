package com.styleguide.repositories;

import com.styleguide.models.ClothingType;
import com.styleguide.models.Piece;
import com.styleguide.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PieceRepository extends CrudRepository<Piece, UUID> {

    List<Piece> findAllByUserAndClothingType(User user, ClothingType type);

}
