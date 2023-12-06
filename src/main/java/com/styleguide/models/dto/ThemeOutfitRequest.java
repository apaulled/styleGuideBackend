package com.styleguide.models.dto;

import com.styleguide.models.enums.Theme;

import java.util.UUID;

public record ThemeOutfitRequest(UUID userId, Theme theme, UserCloset userCloset) {
}
