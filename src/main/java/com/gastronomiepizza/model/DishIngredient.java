package com.gastronomiepizza.model;

import java.time.LocalDate;

public class DishIngredient {
    private String name;
    private Long id;
    private double unitPrice;
    private double quantity ;
    private Unit unit;
    private LocalDate lastChange ;

    public DishIngredient(Long id, String name, double quantity, Unit unit, double unitPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }
    public DishIngredient() {
    }

    public DishIngredient(String name, double unitPrice, Unit unit) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
    }

    public LocalDate getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDate lastChange) {
        this.lastChange = lastChange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
