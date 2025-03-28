package com.td4.service;

import com.td4.DAO.DishDAO;
import com.td4.DTO.DetailledDishDTO;
import com.td4.DTO.DishDTO;
import com.td4.model.Dish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishDAO dishDAO;

    public DishService(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public List<DishDTO> getAllDishes() {
        return dishDAO.getAllDishes();
    }

    public DetailledDishDTO getDishByIdWithIngredients(String id) {
        return dishDAO.getDishById(id);
    }
}