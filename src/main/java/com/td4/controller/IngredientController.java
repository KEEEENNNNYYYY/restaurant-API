package com.td4.controller;


import com.td4.DAO.IngredientDAO;
import com.td4.model.Ingredient;
import com.td4.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Ingredient")
public class IngredientController {
    private final IngredientService ingredientService;
    private IngredientDAO ingredientDAO;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/all")
    public List<Ingredient> getAllIngredient() {
        return ingredientService.getAllIngredient();
    }

}
