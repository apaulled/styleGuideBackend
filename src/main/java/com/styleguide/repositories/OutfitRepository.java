package com.styleguide.repositories;

import com.styleguide.models.Outfit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutfitRepository extends CrudRepository<Outfit, UUID> {
}
