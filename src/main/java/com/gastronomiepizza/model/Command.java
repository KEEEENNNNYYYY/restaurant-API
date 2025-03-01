package com.gastronomiepizza.model;

import java.time.LocalDate;
import java.util.List;

public class Command {
    private String id ;
    private LocalDate commandDate;
    private List<Dish> content;
    private int total;

    public Command(LocalDate commandDate, List<Dish> content, String id, int total) {
        this.commandDate = commandDate;
        this.content = content;
        this.id = id;
        this.total = total;
    }

    public LocalDate getCommandDate() {
        return commandDate;
    }

    public void setCommandDate(LocalDate commandDate) {
        this.commandDate = commandDate;
    }

    public List<Dish> getContent() {
        return content;
    }

    public void setContent(List<Dish> content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
