package com.gastronomiepizza.model;

import com.gastronomiepizza.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import static org.springframework.jdbc.support.JdbcUtils.closeConnection;

public class Ingredient {
    private String id;
    private String name;
    private Map<LocalDate, Double> prices;
    private Unit unit;
    private LocalDate updatedOn;

    public Ingredient(String id, String name, Map<LocalDate, Double> prices, Unit unit, LocalDate updatedOn) {
        this.id = id;
        this.name = name;
        this.prices = prices;
        this.unit = unit;
        this.updatedOn = updatedOn;
    }

    public Ingredient() {

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

    public Map<LocalDate, Double> getPrices() {
        return prices;
    }

    public Double getPriceAt(LocalDate localDate) {
        if(prices.containsKey(localDate)) {
            return prices.get(localDate);
        }
        return null;
    }

    public void setPrices(Map<LocalDate, Double> prices) {
        this.prices = prices;
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

    @Override
    public String toString() {
        return "Ingredient {" +
                "id='" + id + '\'' +
                ", nom='" + name + '\'' +
                ", prices=" + prices +
                ", unit=" + unit +
                ", updatedOn=" + updatedOn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(prices, that.prices) && unit == that.unit && Objects.equals(updatedOn, that.updatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, prices, unit, updatedOn);
    }
}
