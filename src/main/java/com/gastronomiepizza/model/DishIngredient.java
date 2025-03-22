package com.gastronomiepizza.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DishIngredient {
    private String id;
    private String Name ;
    private Double quantity;
    private Unit unit ;
    private Ingredient ingredient;
    private String dishId;

    public DishIngredient(String dishId, String id, Ingredient ingredient, Double quantity, Unit unit) {
        this.dishId = dishId;
        this.id = id;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public DishIngredient(String name, double unitPrice, Unit unit) {
    }

    public DishIngredient(String dishId, String id, Ingredient ingredient, String name, Double quantity, Unit unit) {
        this.dishId = dishId;
        this.id = id;
        this.ingredient = ingredient;
        Name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public DishIngredient(Ingredient saucisse, double v) {
    }

    public DishIngredient() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice(LocalDate date) {
        return quantity * ingredient.getPriceAt(Objects.requireNonNullElseGet(date, LocalDate::now));
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DishIngredient that = (DishIngredient) o;
        return Objects.equals(id, that.id) && Objects.equals(quantity, that.quantity) && Objects.equals(ingredient, that.ingredient) && Objects.equals(dishId, that.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, ingredient, dishId);
    }

    @Override
    public String toString() {
        return "DishIngredient{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", associatedIngredient=" + ingredient +
                ", dishId='" + dishId + '\'' +
                '}';
    }
}
