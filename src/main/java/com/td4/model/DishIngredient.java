package com.td4.model;

import java.time.LocalDate;

public class DishIngredient {
    private String id;
    private String id_ingredient;
    private Double quantity;
    private Ingredient ingredient;
    private String dishId;

    public DishIngredient(String dishId, String id, Ingredient ingredient, Double quantity) {
        this.dishId = dishId;
        this.id = id;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public DishIngredient() {

    }

    public Double getIngredientPrice(){
        return this.quantity * this.ingredient.getPrices();
    }


    public String getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(String id_ingredient) {
        this.id_ingredient = id_ingredient;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

}
