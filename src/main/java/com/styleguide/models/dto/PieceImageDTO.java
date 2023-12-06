package com.styleguide.models.dto;

import com.styleguide.models.enums.ClothingType;
import com.styleguide.models.enums.Color;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record PieceImageDTO(UUID id,
                            String url,
                            Color primaryColor,
                            Color secondaryColor,
                            List<Integer> averageColor,
                            ClothingType clothingType) implements Serializable {
}
