package com.styleguide.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "user_pieces")
public class UserPiece {

    @EmbeddedId
    private Key key;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("pieceId")
    @JoinColumn(name = "piece_id")
    private Piece piece;

    public UserPiece(User user, Piece piece) {
        this.user = user;
        this.piece = piece;
    }

    protected UserPiece() {

    }

    @Embeddable
    public static class Key implements Serializable {
        @Column(name = "user_id")
        private UUID userId;
        @Column(name = "piece_id")
        private UUID pieceId;

        protected Key() {
        }

        public Key(UUID userId, UUID pieceId) {
            this.userId = userId;
            this.pieceId = pieceId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(userId, key.userId) && Objects.equals(pieceId, key.pieceId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, pieceId);
        }

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public UUID getPieceId() {
            return pieceId;
        }

        public void setPieceId(UUID pieceId) {
            this.pieceId = pieceId;
        }
    }

    public User getUser() {
        return user;
    }

    public Piece getPiece() {
        return piece;
    }

    public Key getKey() {
        return key;
    }
}
