package com.anikiej.legoshopdemo.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<LegoSet> basket;

    @Column(name = "ispaid", nullable = false)
    private boolean isPaid;

    @Column(name = "issent", nullable = true)
    private boolean isSent;

    @Column(name = "isdelivered", nullable = true)
    private boolean isDelivered;

    public Transaction(Long id, LocalDateTime time, double value, Currency currency, List<LegoSet> basket, boolean isPaid, boolean isSent, boolean isDelivered) {
        this.id = id;
        this.time = time;
        this.value = value;
        this.currency = currency;
        this.basket = basket;
        this.isPaid = isPaid;
        this.isSent = isSent;
        this.isDelivered = isDelivered;
    }

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<LegoSet> getBasket() {
        return basket;
    }

    public void setBasket(List<LegoSet> basket) {
        this.basket = basket;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

}
