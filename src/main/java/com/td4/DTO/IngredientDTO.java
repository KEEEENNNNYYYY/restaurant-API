package com.td4.DTO;

import com.td4.model.Unit;

import java.time.LocalDate;

public class IngredientDTO {
    private String id;
    private String name;
    private Double UnitPrice;
    private LocalDate updatedAt;

    public IngredientDTO(String id, String name, Double unitPrice, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        UnitPrice = unitPrice;
        this.updatedAt = updatedAt;
    }

    public IngredientDTO() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        UnitPrice = unitPrice;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
