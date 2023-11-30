package com.styleguide.models;

import java.util.List;
import java.util.UUID;

public record PieceColor(UUID id, List<Integer> color) {
}
