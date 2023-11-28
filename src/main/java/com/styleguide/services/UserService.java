package com.styleguide.services;

import com.styleguide.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getUser(UUID id);

    Iterable<User> findAll();

}
