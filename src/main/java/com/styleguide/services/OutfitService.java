package com.styleguide.services;

import com.styleguide.models.enums.Color;
import com.styleguide.models.enums.Theme;

import java.util.UUID;

public interface OutfitService {

    void requestColorOutfit(UUID userId, Color color);

    void requestThemeOutfit(UUID userId, Theme theme);

}
