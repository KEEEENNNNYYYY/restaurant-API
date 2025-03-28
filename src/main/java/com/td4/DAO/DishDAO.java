package com.td4.DAO;

import com.td4.DTO.DetailledDishDTO;
import com.td4.DTO.DishDTO;
import com.td4.Mapper.DishMapper;
import com.td4.model.Dish;
import com.td4.model.DishIngredient;
import com.td4.model.Ingredient;
import com.td4.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DishDAO {

    private final JdbcTemplate jdbcTemplate;
    private DishIngredientDAO dishIngredientDAO;

    @Autowired
    public DishDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.dishIngredientDAO = new DishIngredientDAO(jdbcTemplate);
    }

    public List<DishDTO> getAllDishes() {
        String sql = "SELECT id_dish, name, unit_price FROM dish";
        return jdbcTemplate.query(sql, new DishMapper.DishDTORowMapper());
    }

    public DetailledDishDTO getDishById(String id) {
        String sql = """
    SELECT 
        d.id_dish, d.name, d.unit_price,
        di.id_ingredient, di.required_quantity as quantity,
        i.name as ingredient_name,
        i.unit_price as ingredient_price,
        i.unit as ingredient_unit
    FROM dish d
    LEFT JOIN dish_ingredient di ON d.id_dish = di.id_dish
    LEFT JOIN ingredient i ON di.id_ingredient = i.id_ingredient
    WHERE d.id_dish = ?::integer
    """;

        return jdbcTemplate.query(
                sql,
                rs -> {
                    DetailledDishDTO detailledDishDTO = null;
                    while (rs.next()) {
                        if (detailledDishDTO == null) {
                            // Crée le DTO une seule fois (pour le plat)
                            detailledDishDTO = new DetailledDishDTO();
                            detailledDishDTO.setId_dish(rs.getString("id_dish"));
                            detailledDishDTO.setName(rs.getString("name"));
                            detailledDishDTO.setUnit_price(rs.getDouble("unit_price"));
                            detailledDishDTO.setDishIngredient(new ArrayList<>());
                        }

                        // Ajoute chaque ingrédient (s'il existe)
                        if (rs.getString("id_ingredient") != null) {
                            DishIngredient ingredient = new DishIngredient();
                            ingredient.setId_ingredient(rs.getString("id_ingredient"));
                            ingredient.setQuantity(rs.getDouble("quantity"));

                            // Crée l'objet Ingredient associé
                            Ingredient ingredientDetails = new Ingredient();
                            ingredientDetails.setId(rs.getString("id_ingredient"));
                            ingredientDetails.setName(rs.getString("ingredient_name"));
                            ingredientDetails.setPrices(rs.getDouble("ingredient_price"));
                            //ingredientDetails.setUpdatedOn(rs.getTimestamp("update_datetime").toLocalDateTime().toLocalDate());
                            ingredientDetails.setUnit(Unit.valueOf(rs.getString("ingredient_unit")));
                            //ingredientDetails.setStock(rs.getDouble("stock"));

                            ingredient.setIngredient(ingredientDetails);
                            detailledDishDTO.getDishIngredient().add(ingredient);
                        }
                    }
                    return detailledDishDTO;
                },
                id
        );
    }


}