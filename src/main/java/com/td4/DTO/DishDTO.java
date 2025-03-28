package com.td4.DTO;

import com.td4.model.DishIngredient;

import java.util.List;

public class DishDTO {
    private String id_dish;
    private String name;
    private Double unit_price;

    public DishDTO(String id_dish, String name, Double unit_price) {
        this.id_dish = id_dish;
        this.name = name;
        this.unit_price = unit_price;
    }

    public DishDTO() {

    }

    public String getId_dish() {
        return id_dish;
    }

    public void setId_dish(String id_dish) {
        this.id_dish = id_dish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }
}
