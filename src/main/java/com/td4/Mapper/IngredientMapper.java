package com.td4.Mapper;

import com.td4.DTO.IngredientDTO;
import com.td4.model.Ingredient;
import com.td4.model.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper {
    public static class IngredientDTORowMapper implements RowMapper<IngredientDTO> {
        @Override
        public IngredientDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            IngredientDTO ingredient = new IngredientDTO();
            ingredient.setId(rs.getInt("id_ingredient"));
            ingredient.setName(rs.getString("name"));
            ingredient.setUnitPrice(rs.getDouble("unit_price"));
            ingredient.setUpdatedAt(rs.getTimestamp("update_datetime").toLocalDateTime().toLocalDate());


            return ingredient;
        }
    }
}
