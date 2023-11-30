package com.styleguide.repositories;

import com.styleguide.models.UserPiece;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPieceRepository extends CrudRepository<UserPiece, UserPiece.Key> {
}
