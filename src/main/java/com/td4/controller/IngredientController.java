package com.td4.controller;


import com.td4.DAO.IngredientDAO;
import com.td4.DTO.IngredientDTO;
import com.td4.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Ingredient")
public class IngredientController {
    private final IngredientService ingredientService;
    private IngredientDAO ingredientDAO;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/all")
    public List<IngredientDTO> getAllIngredient() {
        return ingredientService.getAllIngredient();
    }

    @GetMapping("/{id}")
    public IngredientDTO getIngredientById(@PathVariable Integer id) {
        return ingredientService.getIngredientById(id);
    }

}
