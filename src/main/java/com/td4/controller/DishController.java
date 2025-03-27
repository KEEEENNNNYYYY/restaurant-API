package com.td4.controller;

import com.td4.DTO.DishDTO;
import com.td4.model.Dish;
import com.td4.service.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/all")
    public List<DishDTO> getAllDishes() {
        return dishService.getAllDishes();
    }

}