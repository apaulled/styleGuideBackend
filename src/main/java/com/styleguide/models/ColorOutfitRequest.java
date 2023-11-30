package com.styleguide.models;

import java.util.UUID;

public record ColorOutfitRequest(UUID userId,
                                 Color primaryColor,
                                 UserCloset userCloset) {
}
