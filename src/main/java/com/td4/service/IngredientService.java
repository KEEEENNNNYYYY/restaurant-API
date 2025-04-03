package com.td4.service;

import com.td4.DAO.IngredientDAO;
import com.td4.DTO.CreateIngredientRequest;
import com.td4.DTO.IngredientDTO;
import com.td4.model.Criteria;
import com.td4.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    private final IngredientDAO ingredientDAO;

    public IngredientService(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    public List<IngredientDTO> getAllIngredient() {
        return ingredientDAO.getAllIngredient();
    }

    public IngredientDTO getIngredientById(Integer id) {
        return ingredientDAO.getAllIngredient().stream()
                .filter(ingredientDTO -> id.equals(ingredientDTO.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<IngredientDTO> getFilteredIngredients(Criteria criteria) {
        Objects.requireNonNull(criteria, "Le critère de filtrage ne peut pas être null");

        return ingredientDAO.getAllIngredient().stream()
                .filter(ingredient -> matchesPriceCriteria(ingredient, criteria))
                .collect(Collectors.toList());
    }

    private boolean matchesPriceCriteria(IngredientDTO ingredient, Criteria criteria) {
        boolean matchesMin = !criteria.hasPriceMinFilter() || ingredient.getUnitPrice() >= criteria.getPriceMinFilter();
        boolean matchesMax = !criteria.hasPriceMaxFilter() || ingredient.getUnitPrice() <= criteria.getPriceMaxFilter();
        return matchesMin && matchesMax;
    }


    public void addIngredient(CreateIngredientRequest createIngredientRequest){
        ingredientDAO.addIngredient(createIngredientRequest);
    }

    /**
     * the function that need to be fixed below this comment
     * @param ingredients
     * @return
     */
    public List<Ingredient> updateIngredients(List<Ingredient> ingredients) {
        validateIngredients(ingredients);
        ingredients.forEach(ingredient -> {
            if (ingredient.getId() == null) {
                throw new IllegalArgumentException("L'ID est obligatoire pour la mise à jour");
            }
        });
        return ingredientDAO.updateAllIngredients(ingredients);
    }

    private void validateIngredients(List<Ingredient> ingredients) {
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("La liste d'ingrédients ne peut pas être vide");
        }

        ingredients.forEach(ingredient -> {
            if (ingredient.getName() == null || ingredient.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Le nom de l'ingrédient est obligatoire");
            }
            if (ingredient.getPrices() < 0) {
                throw new IllegalArgumentException("Le prix unitaire ne peut pas être négatif");
            }
            if (ingredient.getStock() < 0) {
                throw new IllegalArgumentException("Le stock ne peut pas être négatif");
            }
        });
    }
}