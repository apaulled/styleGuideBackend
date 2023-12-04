package com.styleguide.models.dto;

import com.styleguide.models.Outfit;

import java.util.List;
import java.util.UUID;

public record OutfitBoard(UUID userId, List<Outfit> outfits) {
}
