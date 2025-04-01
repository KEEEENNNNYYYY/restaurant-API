package com.td4.controller;


import com.td4.DAO.IngredientDAO;
import com.td4.DTO.IngredientDTO;
import com.td4.model.Criteria;
import com.td4.model.Ingredient;
import com.td4.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<?> getFilteredIngredients(
            @RequestParam(required = false) Double priceMinFilter,
            @RequestParam(required = false) Double priceMaxFilter
    ) {
        // Validation des paramètres
        if (priceMinFilter != null && priceMinFilter < 0) {
            return ResponseEntity.badRequest().body("priceMinFilter ne peut pas être négatif");
        }
        if (priceMaxFilter != null && priceMaxFilter < 0) {
            return ResponseEntity.badRequest().body("priceMaxFilter ne peut pas être négatif");
        }
        if (priceMinFilter != null && priceMaxFilter != null && priceMinFilter > priceMaxFilter) {
            return ResponseEntity.badRequest().body(
                    String.format("priceMinFilter (%.2f) ne peut pas être supérieur à priceMaxFilter (%.2f)",
                            priceMinFilter, priceMaxFilter)
            );
        }

        Criteria criteria = new Criteria();
        criteria.setPriceMinFilter(priceMinFilter);
        criteria.setPriceMaxFilter(priceMaxFilter);

        List<IngredientDTO> results = ingredientService.getFilteredIngredients(criteria);
        return ResponseEntity.ok(results);
    }


    @PostMapping
    public ResponseEntity<?> createIngredients(@RequestBody List<Ingredient> ingredients) {
        try {
            List<Ingredient> result = ingredientService.createIngredients(ingredients);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateIngredients(@RequestBody List<Ingredient> ingredients) {
        try {
            List<Ingredient> result = ingredientService.updateIngredients(ingredients);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
