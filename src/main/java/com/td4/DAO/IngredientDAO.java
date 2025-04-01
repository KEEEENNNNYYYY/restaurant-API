package com.td4.DAO;

import com.td4.DTO.DishDTO;
import com.td4.DTO.IngredientDTO;
import com.td4.Mapper.DishMapper;
import com.td4.Mapper.IngredientMapper;
import com.td4.model.Ingredient;
import com.td4.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IngredientDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<IngredientDTO> getAllIngredient() {
        String sql = "SELECT * FROM ingredient";
        return jdbcTemplate.query(sql, new IngredientMapper.IngredientDTORowMapper());
    }

    public List<Ingredient> saveAllIngredients(List<Ingredient> ingredients) {
        String sql = "INSERT INTO ingredient (name, unit_price, unit, stock) VALUES (?, ?, ?, ?) RETURNING id_ingredient";
        return ingredients.stream().map(ingredient -> {
            Integer id = jdbcTemplate.queryForObject(sql, new Object[]{
                    ingredient.getName(),
                    ingredient.getPrices(),
                    ingredient.getUnit().name(),
                    ingredient.getStock()
            }, new int[]{
                    Types.VARCHAR,
                    Types.DOUBLE,
                    Types.VARCHAR,
                    Types.INTEGER
            }, Integer.class);
            ingredient.setId(String.valueOf(id));
            return ingredient;
        }).collect(Collectors.toList());
    }

    public List<Ingredient> updateAllIngredients(List<Ingredient> ingredients) {
        String sql = "UPDATE ingredient SET name = ?, unit_price = ?, unit = ?, stock = ? WHERE id_ingredient = ?";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Ingredient ingredient = ingredients.get(i);
                        ps.setString(1, ingredient.getName());
                        ps.setDouble(2, ingredient.getPrices());
                        ps.setString(3, ingredient.getUnit().name()); // Convert Unit to String
                        ps.setDouble(4, ingredient.getStock());
                        ps.setInt(5, Integer.parseInt(ingredient.getId())); // Convert id to Integer
                    }

                    @Override
                    public int getBatchSize() {
                        return ingredients.size();
                    }
                });

        return ingredients;
    }
}
