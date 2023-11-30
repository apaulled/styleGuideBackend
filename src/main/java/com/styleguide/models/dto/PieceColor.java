package com.styleguide.models.dto;

import com.styleguide.models.enums.Color;

import java.util.List;
import java.util.UUID;

public record PieceColor(UUID id, Color primaryColor, Color secondaryColor, List<Integer> averageColor) {
}
