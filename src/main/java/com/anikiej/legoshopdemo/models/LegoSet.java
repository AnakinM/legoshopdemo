package com.anikiej.legoshopdemo.models;

import javax.persistence.*;

@Entity
@Table(name = "legoset")
public class LegoSet {
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "theme", nullable = false)
    private Theme theme;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "piecesnumber", nullable = true)
    private Integer piecesNumber;

    @Column(name = "agerestriction", nullable = true)
    private AgeLimit ageRestriction;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "isavailable", nullable = false)
    private boolean isAvailable;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getPiecesNumber() {
        return piecesNumber;
    }

    public void setPiecesNumber(Integer piecesNumber) {
        this.piecesNumber = piecesNumber;
    }

    public AgeLimit getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeLimit ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return this.quantity > 0;
    }

    public void setIsAvailable() {
        isAvailable = this.quantity > 0;
    }

}
