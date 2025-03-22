package com.gastronomiepizza.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DishOrder {
    private String DishOrderId;
    private String dishId;
    private Double dishQuantity;
    private Status status;
    private LocalDate lastStatusChange;
    private Dish dish;
    private Double unitPrice ;
    private Boolean isMakeable;

    public DishOrder() {
        this.isMakeable = true;
    }

    public Double getTotalPrice(){
        return this.unitPrice * this.dishQuantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getDishOrderPrice(){
        return this.dish.getUnitPrice() * dishQuantity;
    }

    public String getDishOrderId() {
        return DishOrderId;
    }

    public void setDishOrderId(String dishOrderId) {
        DishOrderId = dishOrderId;
    }

    public Status changeStatus(){
        switch (status){
            case CREATED:
                status = Status.CONFIRMED;
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
            case CONFIRMED:
                status = Status.ON_COOCKING;
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
            case ON_COOCKING:
                status = Status.DONE;
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
            case DONE:
                status = Status.SERVED;
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
            case SERVED:
                status = Status.SERVED;
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
            case null, default:
                status = Status.CREATED;
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
        }
        return status;
    }

    public Status getActualStatus(){
        return this.status;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public Double getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(Double dishQuantity) {
        this.dishQuantity = dishQuantity;
    }

    public LocalDate getLastStatusChange() {
        return lastStatusChange;
    }

    public void setLastStatusChange(LocalDate lastStatusChange) {
        this.lastStatusChange = lastStatusChange;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DishOrder(Dish dish, String dishId, Double dishQuantity, LocalDate lastStatusChange, Status status) {
        this.dish = dish;
        this.dishId = dishId;
        this.dishQuantity = dishQuantity;
        this.lastStatusChange = lastStatusChange;
        this.status = status;
        this.isMakeable = true;
    }

    public Boolean getMakeable() {
        return isMakeable;
    }

    public Boolean setMakeable(Boolean makeable) {
        return isMakeable = makeable;
    }

    @Override
    public String toString() {
        return "DishOrder{" +
                "dish=" + dish +
                ", dishId='" + dishId + '\'' +
                ", dishQuantity=" + dishQuantity +
                ", status=" + status +
                ", lastStatusChange=" + lastStatusChange +
                '}';
    }
}
