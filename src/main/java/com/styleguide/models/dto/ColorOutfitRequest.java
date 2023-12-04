package com.styleguide.models.dto;

import com.styleguide.models.enums.Color;

import java.util.UUID;

public record ColorOutfitRequest(UUID userId,
                                 Color color,
                                 UserCloset userCloset) {
}
