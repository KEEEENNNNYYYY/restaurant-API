package com.gastronomiepizza.model;

import com.gastronomiepizza.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.springframework.jdbc.support.JdbcUtils.closeConnection;

public class Ingredient {
    private String name;
    private Long id;
    private Double unitPrice;
    private LocalDate lastChange ;
    private Unit unit;
    protected Long avalaibleStock ;

    public Ingredient(Long id, LocalDate lastChange, String name, Unit unit, Double unitPrice) {
        this.id = id;
        this.lastChange = lastChange;
        this.name = name;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }
    public Ingredient() {

    }

    public Long getAvalaibleStock() {
        return avalaibleStock;
    }

    public void setAvalaibleStock(Long avalaibleStock) {
        this.avalaibleStock = avalaibleStock;
    }

    public LocalDate getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDate lastChange) {
        this.lastChange = lastChange;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", lastChange=" + lastChange +
                ", unit=" + unit +
                '}';
    }
}
