package com.td4.Mapper;

import com.td4.model.Ingredient;
import com.td4.model.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper {
    public static class IngredientRowMapper implements RowMapper<Ingredient> {
        @Override
        public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {

            Ingredient ingredient = new Ingredient();
            ingredient.setId(rs.getString("id_ingredient"));
            ingredient.setName(rs.getString("name"));
            ingredient.setPrices(rs.getDouble("unit_price"));
            ingredient.setUnit(Unit.valueOf(rs.getString("unit")));
            ingredient.setUpdatedOn(rs.getTimestamp("update_datetime").toLocalDateTime().toLocalDate());
            ingredient.setStock(rs.getDouble("stock"));

            return ingredient;
        }
    }
}
