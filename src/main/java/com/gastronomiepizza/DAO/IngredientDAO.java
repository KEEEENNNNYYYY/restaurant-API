package com.gastronomiepizza.DAO;

import com.gastronomiepizza.Database;
import com.gastronomiepizza.model.Criteria;
import com.gastronomiepizza.model.DishIngredient;
import com.gastronomiepizza.model.Ingredient;
import com.gastronomiepizza.model.Unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientDAO {
    /**
     *  function that answer .5 of part 1 :
     * @param criteria
     * @return List<Ingredient>
     * @throws SQLException
     */
    public List<Ingredient> filterBy(Criteria criteria) throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id_ingredient, name, unit_price, unit, update_datetime FROM ingredient WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (criteria.getName() != null) {
            sql.append(" AND name LIKE ?");
            params.add("%" + criteria.getName() + "%");
        }
        if (criteria.getUnit() != null) {
            sql.append(" AND unit = ?");
            params.add(criteria.getUnit().name());
        }
        if (criteria.getMinPrice() != null) {
            sql.append(" AND unit_price >= ?");
            params.add(criteria.getMinPrice());
        }
        if (criteria.getMaxPrice() != null) {
            sql.append(" AND unit_price <= ?");
            params.add(criteria.getMaxPrice());
        }
        if (criteria.getStartDate() != null) {
            sql.append(" AND update_datetime >= ?");
            params.add(criteria.getStartDate());
        }
        if (criteria.getEndDate() != null) {
            sql.append(" AND update_datetime <= ?");
            params.add(criteria.getEndDate());
        }

        if (criteria.getSortBy() != null) {
            List<String> allowedColumns = List.of("name", "unit_price", "update_datetime");
            if (allowedColumns.contains(criteria.getSortBy())) {
                sql.append(" ORDER BY ").append(criteria.getSortBy());
                sql.append(" ").append("desc".equalsIgnoreCase(criteria.getSortOrder()) ? "DESC" : "ASC");
            }
        }

        if (criteria.getPage() != null && criteria.getPageSize() != null) {
            sql.append(" LIMIT ? OFFSET ?");
            params.add(criteria.getPageSize());
            params.add((criteria.getPage() - 1) * criteria.getPageSize());
        }

        Database dataBase = new Database();
        try (Connection conn = dataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ingredient ingredient = new Ingredient();

                    ingredient.setId(rs.getString("id_ingredient"));

                    ingredient.setName(rs.getString("name"));

                    Unit unit = Unit.valueOf(rs.getString("unit"));
                    ingredient.setUnit(unit);

                    LocalDate updatedOn = LocalDate.parse(rs.getString("update_datetime").substring(0, 10));
                    ingredient.setUpdatedOn(updatedOn);

                    Map<LocalDate, Double> prices = new HashMap<>();
                    prices.put(updatedOn, rs.getDouble("unit_price"));
                    ingredient.setPrices(prices);

                    ingredients.add(ingredient);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors du filtrage des ingrédients", e);
        }

        return ingredients;
    }


    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
