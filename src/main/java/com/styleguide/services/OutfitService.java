package com.styleguide.services;

import com.styleguide.models.Color;

import java.util.UUID;

public interface OutfitService {

    void requestColorOutfit(UUID userId, Color color);

}
