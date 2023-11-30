package com.styleguide.models;


import com.styleguide.models.dto.PieceImageDTO;
import com.styleguide.models.enums.ClothingType;
import com.styleguide.models.enums.Color;
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
@Table(name = "pieces", indexes = {
        @Index(name = "pieces_clothing_type_ndx", columnList = "clothing_type"),
})
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

    @Column(name = "primary_color")
    @Enumerated(EnumType.STRING)
    private Color primaryColor;

    @Column(name = "secondary_color")
    @Enumerated(EnumType.STRING)
    private Color secondaryColor;

    @Column(name = "average_color")
    private String averageColor;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "url")
    private String url;

    @Column(name = "clothing_type")
    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Piece(String name) {
        this.name = name;
        //this.imageData = imageData;
    }

    public UUID getId() {
        return id;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public String getAverageColor() {
        return averageColor;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PieceImageDTO toDto() {
        return new PieceImageDTO(id, url, primaryColor, secondaryColor, averageColor, clothingType);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClothingType getClothingType() {
        return clothingType;
    }

    public void setClothingType(ClothingType clothingType) {
        this.clothingType = clothingType;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setAverageColor(String averageColor) {
        this.averageColor = averageColor;
    }
}
