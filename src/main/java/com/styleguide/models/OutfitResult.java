package com.styleguide.models;

import java.util.UUID;

public record OutfitResult(UUID userId,
                           UUID headWear,
                           UUID top,
                           UUID bottom,
                           UUID shoe,
                           UUID outerWear,
                           UUID accessory) {
}
