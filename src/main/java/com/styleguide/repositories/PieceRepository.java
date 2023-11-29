package com.styleguide.repositories;

import com.styleguide.models.Piece;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PieceRepository extends CrudRepository<Piece, UUID> {
}
