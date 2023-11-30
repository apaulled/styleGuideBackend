package com.styleguide.models;

import java.io.Serializable;
import java.util.UUID;

public record PieceImageDTO(UUID id,
                            String url,
                            Color primaryColor,
                            Color secondaryColor,
                            String averageColor,
                            ClothingType clothingType) implements Serializable {
}
