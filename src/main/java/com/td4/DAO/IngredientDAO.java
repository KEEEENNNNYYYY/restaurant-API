package com.td4.DAO;

import com.td4.DTO.CreateIngredientRequest;
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
    /**
     * TODO : utiliser des injections par Constructeur et non autowired
     * TODO optionnel : Add allArgsConstructor
     * TODO : Mapper convertiseur de resultSet en DTO avec "apply"
     *
     * injection par constructeur vs autowired question theorique à l'examen
     * question thoerique 2 : allArgsConstructor vs RequiredArgConstructor
     */
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<IngredientDTO> getAllIngredient() {
        String sql = "SELECT * FROM ingredient";
        return jdbcTemplate.query(sql, new IngredientMapper.IngredientDTORowMapper());
    }

    public void addIngredient(CreateIngredientRequest createIngredientRequest) {
        String sql = "INSERT INTO ingredient (" +
                "id_ingredient, " +
                "name, " +
                "unit_price, " +
                "update_datetime " +
                ") VALUES (?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql,
                    createIngredientRequest.getId(),
                    createIngredientRequest.getName(),
                    createIngredientRequest.getUnit_price(),
                    createIngredientRequest.getUpdate_datetime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Put methode need some fix
     * @param ingredients
     * @return
     */
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
