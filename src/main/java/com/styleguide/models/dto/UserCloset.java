package com.styleguide.models.dto;

import com.styleguide.models.dto.PieceImageDTO;

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
