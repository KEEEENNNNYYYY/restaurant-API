package com.td4.DTO;

import com.td4.model.Unit;

import java.time.LocalDate;
import java.util.Objects;

public class IngredientDTO {
    private Integer id;
    private String name;
    private Double UnitPrice;
    private LocalDate updatedAt;

    public IngredientDTO(Integer id, String name, Double unitPrice, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        UnitPrice = unitPrice;
        this.updatedAt = updatedAt;
    }

    public IngredientDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientDTO that = (IngredientDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(UnitPrice, that.UnitPrice) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, UnitPrice, updatedAt);
    }

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
