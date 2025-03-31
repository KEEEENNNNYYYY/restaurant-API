package com.td4.service;

import com.td4.DAO.IngredientDAO;
import com.td4.DTO.IngredientDTO;
import com.td4.model.Criteria;
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

    /*public List<IngredientDTO> getFilteredIngredients(Criteria criteria) {
        return ingredientDAO.getAllIngredient().stream()
                .filter(ingredient ->
                        (criteria.getPriceMinFilter() == null || ingredient.getUnitPrice() >= criteria.getPriceMinFilter()) &&
                                (criteria.getPriceMaxFilter() == null || ingredient.getUnitPrice() <= criteria.getPriceMaxFilter())
                )
                .collect(Collectors.toList());
    }*/
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
}