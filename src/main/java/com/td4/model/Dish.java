package com.td4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String id;
    private String name;
    private Double fixUnitPrice;
    private List<DishIngredient> ingredients;

    public Dish(Double fixUnitPrice, String id, List<DishIngredient> ingredients, String name) {
        this.fixUnitPrice = fixUnitPrice;
        this.id = id;
        this.ingredients = new ArrayList<>();
        this.name = name;
    }

    public Dish() {
        this.ingredients = new ArrayList<>();
    }
    public Double getDishIngredientCost(){
        Double cost = 0.0;
        for(DishIngredient ingredient : ingredients){
            cost += ingredient.getIngredientPrice();
        }
        return cost;
    }






    public Double getFixUnitPrice(double price) {
        return fixUnitPrice;
    }

    public void setFixUnitPrice(Double fixUnitPrice) {
        this.fixUnitPrice = fixUnitPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DishIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<DishIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
