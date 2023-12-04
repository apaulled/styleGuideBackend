package com.styleguide.repositories;

import com.styleguide.models.Outfit;
import com.styleguide.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutfitRepository extends CrudRepository<Outfit, UUID> {

    List<Outfit> findAllByUser(User user);
}
