package com.styleguide.models.dto;

import java.util.List;
import java.util.UUID;

public record OutfitResult(UUID userId,
                           List<OutfitDto> outfits) {
}
