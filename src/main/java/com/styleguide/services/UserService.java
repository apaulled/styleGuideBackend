package com.styleguide.services;

import com.styleguide.models.User;
import com.styleguide.models.dto.OutfitBoard;
import com.styleguide.models.dto.UserCloset;
import com.styleguide.models.dto.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse getUser(UUID id);

    OutfitBoard getOutfitsForUser(UUID id);

    UserCloset getUserCloset(UUID id);

    Iterable<User> findAll();

    UserResponse login(String username, String password);

    UserResponse register(String username, String password);

}
