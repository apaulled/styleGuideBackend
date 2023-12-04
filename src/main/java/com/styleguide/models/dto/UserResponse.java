package com.styleguide.models.dto;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(UUID userId,
                           String name,
                           Instant createdAt,
                           UserCloset closet,
                           OutfitBoard outfits) {
}
