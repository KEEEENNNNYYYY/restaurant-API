package com.gastronomiepizza.model;

import com.gastronomiepizza.model.Unit;
import java.time.LocalDate;

public class Criteria {
    private String name;
    private Unit unit;
    private Double minPrice;
    private Double maxPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String sortBy;
    private String sortOrder;
    private Integer page;
    private Integer pageSize;

    // Constructeur vide pour permettre une utilisation flexible
    public Criteria() {}

    // Getters et Setters (pour permettre de choisir les filtres)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Unit getUnit() { return unit; }
    public void setUnit(Unit unit) { this.unit = unit; }

    public Double getMinPrice() { return minPrice; }
    public void setMinPrice(Double minPrice) { this.minPrice = minPrice; }

    public Double getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Double maxPrice) { this.maxPrice = maxPrice; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }

    public String getSortOrder() { return sortOrder; }
    public void setSortOrder(String sortOrder) { this.sortOrder = sortOrder; }

    public Integer getPage() { return page; }
    public void setPage(Integer page) { this.page = page; }

    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}
