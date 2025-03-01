package com.gastronomiepizza.model;

import java.util.List;

public class Stock {
    private String location ;
    private List<Ingredient>ingredient ;

    public Stock(String location, List<Ingredient> ingredient) {
        this.location = location;
        this.ingredient = ingredient;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }
}
