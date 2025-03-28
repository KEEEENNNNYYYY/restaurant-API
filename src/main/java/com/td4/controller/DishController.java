package com.td4.controller;

import com.td4.DAO.DishDAO;
import com.td4.DTO.DetailledDishDTO;
import com.td4.DTO.DishDTO;
import com.td4.model.Dish;
import com.td4.service.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    private final DishService dishService;
    private DishDAO dishDAO;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/all")
    public List<DishDTO> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    public DetailledDishDTO getDishById(@PathVariable String id) {
        return dishService.getDishByIdWithIngredients(id);
    }

}