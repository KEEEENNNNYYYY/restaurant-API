package com.td4.Mapper;

import com.td4.DTO.DishDTO;
import com.td4.model.Dish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishMapper {
    /**
     * return a dish object with only the collumn inside the table on postgres
     */
    public static class DishDTORowMapper implements RowMapper<DishDTO> {
        @Override
        public DishDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            DishDTO dish = new DishDTO();
            dish.setId_dish(rs.getString("id_dish"));
            dish.setName(rs.getString("name"));
            dish.setUnit_price(rs.getDouble("unit_price"));

            return dish;
        }
    }
}
