package com.td4.model;

import java.time.LocalDate;
import java.util.Map;

public class Ingredient {
    private String id;
    private String name;
    private Double prices;
    private Unit unit;
    private LocalDate updatedOn;
    private Double stock;

    public Ingredient(String id, String name, Double prices, Double stock, Unit unit) {
        this.id = id;
        this.name = name;
        this.prices = prices;
        this.stock = stock;
        this.unit = unit;
        this.updatedOn = LocalDate.now() ;
    }

    public Ingredient() {

    }

    public Double setPrices(Double prices) {
        Double price = 0.0;
        LocalDate date = LocalDate.now();
        if(prices > 0){
            price = prices;
            this.prices = price;
            this.updatedOn = date;
        }else{
            System.out.println("Price must be greater than 0");
        }
        return price;
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

    public Double getPrices() {
        return prices;
    }


    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }
}
