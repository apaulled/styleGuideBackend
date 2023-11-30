package com.styleguide.models;

import java.io.Serializable;
import java.util.UUID;

public record PieceImageDTO(UUID id, String url) implements Serializable {
}
