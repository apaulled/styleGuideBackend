package com.styleguide.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outfits")
public class Outfit {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "head_wear", nullable = false)
    private Piece headWear;

    @ManyToOne
    @JoinColumn(name = "top", nullable = false)
    private Piece top;

    @ManyToOne
    @JoinColumn(name = "bottom", nullable = false)
    private Piece bottom;

    @ManyToOne
    @JoinColumn(name = "shoe", nullable = false)
    private Piece shoe;

    @ManyToOne
    @JoinColumn(name = "outer_wear", nullable = false)
    private Piece outerWear;

    @ManyToOne
    @JoinColumn(name = "accessory", nullable = false)
    private Piece accessory;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    protected Outfit() {

    }

    public Outfit(User user, Piece headWear, Piece top,
                  Piece bottom, Piece shoe, Piece outerWear,
                  Piece accessory) {
        this.user = user;
        this.headWear = headWear;
        this.top = top;
        this.bottom = bottom;
        this.shoe = shoe;
        this.outerWear = outerWear;
        this.accessory = accessory;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Piece getHeadWear() {
        return headWear;
    }

    public void setHeadWear(Piece headWear) {
        this.headWear = headWear;
    }

    public Piece getTop() {
        return top;
    }

    public void setTop(Piece top) {
        this.top = top;
    }

    public Piece getBottom() {
        return bottom;
    }

    public void setBottom(Piece bottom) {
        this.bottom = bottom;
    }

    public Piece getShoe() {
        return shoe;
    }

    public void setShoe(Piece shoe) {
        this.shoe = shoe;
    }

    public Piece getOuterWear() {
        return outerWear;
    }

    public void setOuterWear(Piece outerWear) {
        this.outerWear = outerWear;
    }

    public Piece getAccessory() {
        return accessory;
    }

    public void setAccessory(Piece accessory) {
        this.accessory = accessory;
    }
}
