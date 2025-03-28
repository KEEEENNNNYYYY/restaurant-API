package com.td4.DAO;

import com.td4.DTO.DishDTO;
import com.td4.DTO.IngredientDTO;
import com.td4.Mapper.DishMapper;
import com.td4.Mapper.IngredientMapper;
import com.td4.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /*public Ingredient getIngredientById(String id) {
        String sql = "SELECT * FROM ingredient WHERE id_ingredient = ?";
        return jdbcTemplate.queryForObject(sql, new IngredientMapper.IngredientRowMapper(), id);
    }*/
}
