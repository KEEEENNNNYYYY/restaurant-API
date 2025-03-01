package com.gastronomiepizza.model;

import java.util.List;

public class NewDishRequest {
    private Long id;
    private String name;
    private List<DishIngredient> recipe;
    private Double price;

    public NewDishRequest(Long id, String name, Double price, List<DishIngredient> recipe) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.recipe = recipe;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<DishIngredient> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<DishIngredient> recipe) {
        this.recipe = recipe;
    }
}
