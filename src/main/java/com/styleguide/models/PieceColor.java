package com.styleguide.models;

import java.util.List;
import java.util.UUID;

public record PieceColor(UUID id, Color primaryColor, Color secondaryColor, List<Integer> averageColor) {
}
