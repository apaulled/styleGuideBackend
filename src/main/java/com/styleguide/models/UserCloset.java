package com.styleguide.models;

import java.util.List;
import java.util.UUID;

public record UserCloset(UUID userId,
                         List<PieceImageDTO> headWear,
                         List<PieceImageDTO> tops,
                         List<PieceImageDTO> bottoms,
                         List<PieceImageDTO> shoes,
                         List<PieceImageDTO> outerWear,
                         List<PieceImageDTO> accessories) {
}
