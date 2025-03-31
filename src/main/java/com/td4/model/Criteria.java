package com.td4.model;

import java.util.Objects;

public class Criteria {
    private Double priceMinFilter;
    private Double priceMaxFilter;

    // Constructeur par défaut nécessaire pour Spring
    public Criteria() {}

    // Getters et Setters
    public Double getPriceMinFilter() {
        return priceMinFilter;
    }

    public void setPriceMinFilter(Double priceMinFilter) {
        this.priceMinFilter = priceMinFilter;
    }

    public Double getPriceMaxFilter() {
        return priceMaxFilter;
    }

    public void setPriceMaxFilter(Double priceMaxFilter) {
        this.priceMaxFilter = priceMaxFilter;
    }

    // Méthodes utilitaires
    public boolean hasPriceMinFilter() {
        return priceMinFilter != null;
    }

    public boolean hasPriceMaxFilter() {
        return priceMaxFilter != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criteria criteria = (Criteria) o;
        return Objects.equals(priceMinFilter, criteria.priceMinFilter) &&
                Objects.equals(priceMaxFilter, criteria.priceMaxFilter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceMinFilter, priceMaxFilter);
    }
}