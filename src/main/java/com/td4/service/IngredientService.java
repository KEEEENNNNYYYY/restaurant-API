package com.td4.service;

import com.td4.DAO.DishDAO;
import com.td4.DAO.IngredientDAO;
import com.td4.DTO.DishDTO;
import com.td4.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientDAO ingredientDAO;

    public IngredientService(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    public List<Ingredient> getAllIngredient() {
        return ingredientDAO.getAllIngredient();
    }
}