package com.td4.DAO;

import com.td4.DTO.DishDTO;
import com.td4.Mapper.DishMapper;
import com.td4.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DishDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DishDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DishDTO> getAllDishes() {
        String sql = "SELECT id_dish, name, unit_price FROM dish";
        return jdbcTemplate.query(sql, new DishMapper.DishDTORowMapper());
    }


}