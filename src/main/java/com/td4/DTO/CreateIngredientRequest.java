package com.td4.DTO;

import com.td4.model.Unit;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class CreateIngredientRequest {
    private Long id;
    private String name;

    @JsonProperty("unitPrice") // 👈 Map "unitPrice" JSON vers ce champ
    private Double unit_price;

    @JsonProperty("updatedAt") // 👈 Map "updatedAt" JSON vers ce champ
    private LocalDateTime update_datetime;

    // Getters/Setters (obligatoires)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getUnit_price() { return unit_price; }
    public void setUnit_price(Double unit_price) { this.unit_price = unit_price; }

    public LocalDateTime getUpdate_datetime() { return update_datetime; }
    public void setUpdate_datetime(LocalDateTime update_datetime) { this.update_datetime = update_datetime; }
}