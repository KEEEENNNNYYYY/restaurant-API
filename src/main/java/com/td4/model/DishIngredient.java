package com.td4.model;

import java.time.LocalDate;

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

    public DishIngredient() {

    }

    public Double getIngredientPrice(){
        return this.quantity * this.ingredient.getPrices();
    }










    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
