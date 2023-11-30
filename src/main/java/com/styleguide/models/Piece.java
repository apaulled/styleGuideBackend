package com.styleguide.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "pieces")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Piece {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @Column(name = "name")
    private String name;

    //@Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    @Column(name = "primary_color")
    private String primaryColor;

    @Column(name = "secondary_color")
    private String secondaryColor;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "url")
    private String url;

    public Piece(String name, byte[] imageData) {
        this.name = name;
        this.imageData = imageData;
    }

    public UUID getId() {
        return id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PieceImageDTO toDto() {
        return new PieceImageDTO(id, url);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
