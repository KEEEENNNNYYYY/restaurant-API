package com.gastronomiepizza.model;

import com.gastronomiepizza.DAO.DishDAO;
import com.gastronomiepizza.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Dish {
    private String id;
    private String name;
    private Double unitPrice;
    private List<DishIngredient> ingredients;

    public Dish(String id, String name, Double unitPrice, List<DishIngredient> ingredients) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.ingredients = ingredients;
    }

    public Dish() {

    }

    public Dish(String dish1, String hotDog, double v) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * function that answer the question .6-a or part 1 :
     * @param dishIngredients
     * @param date
     * @return
     * @throws SQLException
     */
    public Double getIngredientsCost(List<DishIngredient> dishIngredients ,LocalDate date ) throws SQLException {
        Database dataBase = new Database();
        Connection conn = dataBase.getConnection();

        Double totalPrice = 0.0;
        for (DishIngredient dishIngredient : dishIngredients) {
            String query = "SELECT unit_price FROM ingredient WHERE id_ingredient = ? AND update_datetime <= ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, dishIngredient.getId());
                preparedStatement.setDate(2, java.sql.Date.valueOf(date));

                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    double price = rs.getDouble("unit_price");
                    totalPrice += price * dishIngredient.getQuantity();
                }
            } catch (SQLException e) {
                throw new SQLException("Erreur lors du calcul du coût des ingrédients", e);
            }
        }
        return totalPrice;
    }

    /**
     * function that answer question .7 of part 1 :
     * @return
     * @throws SQLException
     */
    public Double getGrossMargin() throws SQLException {
        DishDAO dishDAO = new DishDAO();
        Long dishId = Long.parseLong(this.id);

        Double dishRecipePrice = dishDAO.getDishRecipePrice(dishId);

        Double marginPrice = this.getUnitPrice()-dishRecipePrice;
        System.out.println("margin prrice : "+ marginPrice);

        return marginPrice;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<DishIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<DishIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name) && Objects.equals(unitPrice, dish.unitPrice) && Objects.equals(ingredients, dish.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitPrice, ingredients);
    }
}
