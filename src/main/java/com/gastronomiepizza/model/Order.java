package com.gastronomiepizza.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private String reference;
    private LocalDate OrderDate;
    private List <DishOrder> dishOrders;
    private Status status;
    private LocalDate lastStatusChange;
    private List <Status> orderListStatus;

    public Order(List<DishOrder> dishOrders, String id, LocalDate lastStatusChange, LocalDate orderDate, String reference, Status status) {
        this.dishOrders = dishOrders;
        this.id = id;
        this.lastStatusChange = lastStatusChange;
        OrderDate = orderDate;
        this.reference = reference;
        this.status = status;
    }

    public Order() {
    }

    public List<Status> getOrderListStatus() {
        List<Status> listOfStatus = new ArrayList<>();
        for (DishOrder dishOrder : dishOrders) {
            listOfStatus.add(dishOrder.getStatus());
        }
        this.orderListStatus = listOfStatus;
        return this.orderListStatus;
    }

    public List<DishOrder> getDishOrders() {
        return dishOrders;
    }

    public List<DishOrder> setDishOrders(List<DishOrder> dishOrders) {
        List<DishOrder> makeableDishOrders = new ArrayList<>();
        for (DishOrder dishOrder : dishOrders) {
            Boolean makeable = dishOrder.getMakeable();
            if (makeable == null || !makeable) {
                System.out.println("A dish is not makeable or doesn't exist yet");
            }
            else if (makeable){
                makeableDishOrders.add(dishOrder);
            }

        }
        this.dishOrders = makeableDishOrders;
        return this.dishOrders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setLastStatusChange(LocalDate lastStatusChange) {
        this.lastStatusChange = lastStatusChange;
    }



    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getActualStatus(){
        return this.status;
    }

    public List <DishOrder> getDishOrder(){
        return this.dishOrders;
    }

    public Double getTotalAmount(){
        Double totalPrice = 0.0;
        for (DishOrder dishOrder : dishOrders){
            totalPrice += dishOrder.getDishOrderPrice();
        }
        return totalPrice;
    }

    /**
     * Change status of all dishOrder.status when order.status change
     * @return
     */
    public Status statusChange(){
        switch (status){
            case CREATED:
                int CreatedCount = 0;
                for(DishOrder dishOrder : dishOrders){
                    if(dishOrder.getActualStatus() == Status.CREATED){
                        CreatedCount++;
                    }
                }
                if (CreatedCount == this.dishOrders.toArray().length){
                    status = Status.CONFIRMED;
                    for(DishOrder dishOrder : dishOrders){
                        dishOrder.setStatus(Status.CONFIRMED);
                    }
                }
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
            case CONFIRMED:
                int ConfirmedCount = 0;
                for(DishOrder dishOrder : dishOrders){
                    if(dishOrder.getActualStatus() == Status.CONFIRMED){
                        ConfirmedCount++;
                    }
                }
                if (ConfirmedCount == this.dishOrders.toArray().length){
                    status = Status.ON_COOCKING;
                    for(DishOrder dishOrder : dishOrders){
                        dishOrder.setStatus(Status.ON_COOCKING);
                    }
                }
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
            case ON_COOCKING:
                int onCoockingCount = 0;
                for(DishOrder dishOrder : dishOrders){
                    if(dishOrder.getActualStatus() == Status.ON_COOCKING){
                        onCoockingCount++;
                    }
                }
                if (onCoockingCount == this.dishOrders.toArray().length){
                    status = Status.DONE;
                    for(DishOrder dishOrder : dishOrders){
                        dishOrder.setStatus(Status.DONE);
                    }
                }
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
            case DONE:
                int DoneCount = 0;
                for(DishOrder dishOrder : dishOrders){
                    if(dishOrder.getActualStatus() == Status.DONE){
                        DoneCount++;
                    }
                }
                if (DoneCount == this.dishOrders.toArray().length){
                    status = Status.SERVED;
                    for(DishOrder dishOrder : dishOrders){
                        dishOrder.setStatus(Status.SERVED);
                    }
                }
                else{
                    status = Status.DONE;
                }
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
            case null, default:
                status = Status.CREATED;
                lastStatusChange = LocalDate.from(LocalDateTime.now());
                break;
        }
        return status;
    }

    @Override
    public String toString() {
        return null;
    }

    public void setOrderDate(LocalDate createdAt) {
    }
}
