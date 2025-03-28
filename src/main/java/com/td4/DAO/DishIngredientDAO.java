package com.td4.DAO;

import com.td4.Mapper.DishIngredientMapper;
import com.td4.Mapper.IngredientMapper;
import com.td4.model.DishIngredient;
import com.td4.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DishIngredientDAO {

    private final JdbcTemplate jdbcTemplate;
    private IngredientDAO ingredientDAO;

    @Autowired
    public DishIngredientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.ingredientDAO = new IngredientDAO(jdbcTemplate);
    }

}
