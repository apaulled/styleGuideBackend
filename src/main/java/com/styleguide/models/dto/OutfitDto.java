package com.styleguide.models.dto;

import java.util.UUID;

public record OutfitDto(UUID headWear,
                        UUID top,
                        UUID bottom,
                        UUID shoe,
                        UUID outerWear,
                        UUID accessory) {
}
