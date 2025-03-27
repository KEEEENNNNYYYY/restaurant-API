package com.td4.Mapper;

import com.td4.model.DishIngredient;
import com.td4.model.Ingredient;
import com.td4.model.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishIngredientMapper {
    public static class DishIngredientRowMapper implements RowMapper<DishIngredient> {
        @Override
        public DishIngredient mapRow(ResultSet rs, int rowNum) throws SQLException {
            DishIngredient dishIngredient = new DishIngredient();

            // Mapping des champs de base
            dishIngredient.setDishId(rs.getString("id_dish"));
            dishIngredient.setId_ingredient(rs.getString("id_ingredient"));
            dishIngredient.setQuantity(rs.getDouble("required_quantity"));


            Ingredient ingredient = new Ingredient();
            ingredient.setId(rs.getString("id_ingredient"));
            ingredient.setName(rs.getString("ingredient_name"));
            ingredient.setPrices(rs.getDouble("ingredient_price"));
            ingredient.setUnit(Unit.valueOf(rs.getString("ingredient_unit")));

            dishIngredient.setIngredient(ingredient);

            return dishIngredient; // Retourne l'objet mappé
        }
    }
}