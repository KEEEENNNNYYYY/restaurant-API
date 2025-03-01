package com.gastronomiepizza.DAO;

import com.gastronomiepizza.Database;
import com.gastronomiepizza.model.*;
import org.springframework.aot.generate.InMemoryGeneratedFiles;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishDAO {

    public List<Dish> getAllDishes() {
        Database dataBase = new Database();
        Connection conn = dataBase.getConnection();
        List<Dish> dishList = new ArrayList<>();

        if (conn != null) {
            String sql = "SELECT id_dish, name, price FROM dish";
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Dish dish = new Dish();
                    dish.setId(rs.getLong("id_dish"));
                    dish.setName(rs.getString("name"));
                    dish.setPrice(rs.getDouble("price"));
                    dishList.add(dish);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error retrieving dishes from database", e);
            } finally {
                closeConnection(conn);
            }
        }
        return dishList;
    }

    public Dish getDishById(int idToFind, LocalDate date) {
        Database dataBase = new Database();
        Connection conn = dataBase.getConnection();
        Dish dish = null;

        if (conn != null) {
            String sql = "SELECT id_dish, name FROM dish WHERE id_dish = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idToFind);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        dish = new Dish();
                        dish.setId(rs.getLong("id_dish"));
                        dish.setName(rs.getString("name"));

                        // Récupérer les ingrédients
                        List<DishIngredient> dishIngredients = getDishRecipe(idToFind, date);
                        dish.setRecipe(dishIngredients);

                        // Calcul du prix total basé sur les ingrédients
                        Double totalPrice = 0.0;
                        for (DishIngredient ingredient : dishIngredients) {
                            totalPrice += ingredient.getUnitPrice() * ingredient.getQuantity();
                        }
                        dish.setPrice(totalPrice);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la récupération du plat depuis la base de données", e);
            } finally {
                closeConnection(conn);
            }
        }
        return dish;
    }

    public List<DishIngredient> getDishRecipe(int idDish, LocalDate date) {
        Database dataBase = new Database();
        Connection conn = dataBase.getConnection();
        List<DishIngredient> dishIngredientList = new ArrayList<>();

        if (conn != null) {
            String sql = "SELECT i.name AS ingredient_name, di.required_quantity AS required_quantity , di.unit, di.unit_price \n" +
                    "FROM dish_ingredient di \n" +
                    "JOIN ingredient i ON di.id_ingredient = i.id_ingredient \n" +
                    "WHERE di.id_dish = ? AND i.update_datetime <= ? \n" +
                    "ORDER BY i.name DESC;";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idDish);
                pstmt.setDate(2, Date.valueOf(date));
                boolean hasResult = pstmt.execute();

                while (hasResult) {
                    try (ResultSet rs = pstmt.getResultSet()) {
                        while (rs.next()) {
                            DishIngredient dishIngredient = new DishIngredient();
                            dishIngredient.setName(rs.getString("ingredient_name"));
                            dishIngredient.setQuantity(rs.getDouble("required_quantity"));
                            dishIngredient.setUnit(Unit.valueOf(rs.getString("unit")));
                            dishIngredient.setUnitPrice(rs.getDouble("unit_price"));

                            dishIngredientList.add(dishIngredient);
                        }
                    }
                    hasResult = pstmt.getMoreResults();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la récupération des ingrédients du plat", e);
            } finally {
                closeConnection(conn);
            }
        }
        return dishIngredientList;
    }

    public List<DishIngredient> filterSortPaginateIngredients(Criteria criteria) {
        Database dataBase = new Database();
        Connection conn = null;
        List<DishIngredient> ingredients = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ingredient WHERE 1=1");

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
            params.add(Date.valueOf(criteria.getStartDate()));
        }
        if (criteria.getEndDate() != null) {
            sql.append(" AND update_datetime <= ?");
            params.add(Date.valueOf(criteria.getEndDate()));
        }

        if (criteria.getSortBy() != null) {
            sql.append(" ORDER BY ").append(criteria.getSortBy());
            sql.append(" ").append("desc".equalsIgnoreCase(criteria.getSortOrder()) ? "DESC" : "ASC");
        }

        if (criteria.getPage() != null && criteria.getPageSize() != null) {
            sql.append(" LIMIT ? OFFSET ?");
            params.add(criteria.getPageSize());
            params.add((criteria.getPage() - 1) * criteria.getPageSize());
        }

        try {
            conn = dataBase.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

                for (int i = 0; i < params.size(); i++) {
                    stmt.setObject(i + 1, params.get(i));
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        ingredients.add(new DishIngredient(
                                rs.getString("name"),
                                rs.getDouble("unit_price"),
                                Unit.valueOf(rs.getString("unit"))
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return ingredients;
    }




    /***********/

    public List<DishIngredient> getRecipe(int idDish) {
        Database dataBase = new Database();
        Connection conn = dataBase.getConnection();
        List<DishIngredient> dishIngredientList = new ArrayList<>();

        if (conn != null) {
            String sql = "SELECT i.name AS ingredient_name, di.required_quantity AS required_quantity , di.unit, di.unit_price \n" +
                    "FROM dish_ingredient di \n" +
                    "JOIN ingredient i ON di.id_ingredient = i.id_ingredient \n" +
                    "WHERE di.id_dish = ?  \n" +
                    "ORDER BY i.name DESC;";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idDish);
                boolean hasResult = pstmt.execute();

                while (hasResult) {
                    try (ResultSet rs = pstmt.getResultSet()) {
                        while (rs.next()) {
                            DishIngredient dishIngredient = new DishIngredient();
                            dishIngredient.setName(rs.getString("ingredient_name"));
                            dishIngredient.setQuantity(rs.getDouble("required_quantity"));
                            dishIngredient.setUnit(Unit.valueOf(rs.getString("unit")));
                            dishIngredient.setUnitPrice(rs.getDouble("unit_price"));


                            dishIngredientList.add(dishIngredient);
                        }
                    }
                    hasResult = pstmt.getMoreResults();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la récupération des ingrédients du plat", e);
            } finally {
                closeConnection(conn);
            }
        }
        return dishIngredientList;
    }

    public List<Ingredient> getStock(int idDish){
        Database dataBase = new Database();
        Connection conn = dataBase.getConnection();
        List<Ingredient> dishIngredientList = new ArrayList<>();

        if (conn != null) {
            String sql = "SELECT * FROM ingredient";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                boolean hasResult = pstmt.execute();

                while (hasResult) {
                    try (ResultSet rs = pstmt.getResultSet()) {
                        while (rs.next()) {
                            Ingredient dishIngredient = new Ingredient();
                            dishIngredient.setName(rs.getString("id_ingredient"));
                            dishIngredient.setAvalaibleStock(rs.getLong("stock"));
                            dishIngredient.setUnitPrice(rs.getDouble("unit_price"));

                            dishIngredientList.add(dishIngredient);
                        }
                    }
                    hasResult = pstmt.getMoreResults();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la récupération des ingrédients du plat", e);
            } finally {
                closeConnection(conn);
            }
        }
        return dishIngredientList;
    }

    public int getMakableDish(int idDish) {
        List<DishIngredient> dishIngredients = getRecipe(idDish);
        List<Ingredient> stockIngredients = getStock(idDish);

        int maxDishes = Integer.MAX_VALUE;
        System.out.println("Ingrédients pour le plat ID " + idDish + ":");
        for (DishIngredient dishIngredient : dishIngredients) {
            System.out.println("Ingrédient: " + dishIngredient.getName() + ", Quantité requise: " + dishIngredient.getQuantity());
        }

        System.out.println("Stock disponible pour les ingrédients:");
        for (Ingredient stockIngredient : stockIngredients) {
            System.out.println("Ingrédient: " + stockIngredient.getName() + ", Quantité disponible: " + stockIngredient.getAvalaibleStock());
        }

        for (DishIngredient dishIngredient : dishIngredients) {

            Ingredient stockIngredient = stockIngredients.stream()
                    .filter(i -> i.getName().equals(dishIngredient.getName()))
                    .findFirst()
                    .orElse(null);

            if (stockIngredient != null) {
                double makableWithIngredient = stockIngredient.getAvalaibleStock() / dishIngredient.getQuantity();
                System.out.println("Ingrédient trouvé: " + dishIngredient.getName() + " -> Stock disponible: "
                        + stockIngredient.getAvalaibleStock() + ", Quantité requise: "
                        + dishIngredient.getQuantity() + ", Plats réalisables avec cet ingrédient: "
                        + makableWithIngredient);

                maxDishes = Math.min(maxDishes, (int) makableWithIngredient);
            } else {
                System.out.println("Ingrédient manquant dans le stock: " + dishIngredient.getName());
                maxDishes = 0;
                break;
            }
        }

        System.out.println("Nombre de plats réalisables: " + maxDishes);
        return maxDishes;
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
