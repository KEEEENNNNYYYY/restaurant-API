package com.gastronomiepizza.DAO;

import com.gastronomiepizza.Database;
import com.gastronomiepizza.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public void createOrder(Order order) throws SQLException {
        Database database = new Database();
        Connection conn = database.getConnection();

        if (conn != null) {
            String sql = "INSERT INTO \"Order\" (id_order, reference, created_at, status, last_status_change) VALUES (?, ?, ?, ?::status, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, order.getId());
                pstmt.setString(2, order.getReference());
                pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setString(4, order.getStatus().name());
                pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la création de la commande", e);
            } finally {
                closeConnection(conn);
            }
        }
    }
    // Méthode pour récupérer une commande par son ID
    public Order getOrderById(String orderId) throws SQLException {
        Database database = new Database();
        Connection conn = database.getConnection();
        Order order = null;

        if (conn != null) {
            String sql = "SELECT * FROM \"Order\" WHERE id_order = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, orderId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        order = new Order();
                        order.setId(rs.getString("id_order"));
                        order.setReference(rs.getString("reference"));
                        order.setOrderDate(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
                        order.setStatus(Status.valueOf(rs.getString("status")));
                        order.setLastStatusChange(rs.getTimestamp("last_status_change").toLocalDateTime().toLocalDate());

                        // Récupérer les DishOrder associés à cette commande
                        List<DishOrder> dishOrders = getDishOrdersByOrderId(orderId);
                        order.setDishOrders(dishOrders);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la récupération de la commande", e);
            } finally {
                closeConnection(conn);
            }
        }
        return order;
    }

    public void saveOrder(String orderId, Status newStatus) throws SQLException {
        Database database = new Database();
        Connection conn = database.getConnection();

        if (conn != null) {
            String sql = "UPDATE \"Order\" SET status = (?::status), last_status_change = ? WHERE id_order = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newStatus.name());
                pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setString(3, orderId);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la mise à jour du statut de la commande", e);
            } finally {
                closeConnection(conn);
            }
        }
    }

    public void saveDishOrder(String dishOrderId, Status newStatus) throws SQLException {
        Database database = new Database();
        Connection conn = database.getConnection();

        if (conn != null) {
            String sql = "UPDATE dishorder SET status = (?::status), last_status_change = ? WHERE dish_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newStatus.name());
                pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setString(3, dishOrderId);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la mise à jour du statut du plat", e);
            } finally {
                closeConnection(conn);
            }
        }
    }


    // Méthode pour récupérer les DishOrder d'une commande
    private List<DishOrder> getDishOrdersByOrderId(String orderId) throws SQLException {
        Database database = new Database();
        Connection conn = database.getConnection();
        List<DishOrder> dishOrders = new ArrayList<>();

        if (conn != null) {
            String sql = "SELECT * FROM dishorder WHERE order_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, orderId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        DishOrder dishOrder = new DishOrder();
                        dishOrder.setDishId(rs.getString("dish_id"));
                        dishOrder.setDishQuantity(rs.getDouble("dish_quantity"));
                        dishOrder.setStatus(Status.valueOf(rs.getString("status")));
                        dishOrder.setLastStatusChange(rs.getTimestamp("last_status_change").toLocalDateTime().toLocalDate());

                        // Récupérer le plat (Dish) associé
                        DishDAO dishDAO = new DishDAO();
                        Dish dish = dishDAO.getDishById(rs.getInt("dish_id"));
                        dishOrder.setDish(dish);

                        dishOrders.add(dishOrder);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la récupération des plats de la commande", e);
            } finally {
                closeConnection(conn);
            }
        }
        return dishOrders;
    }
    // Méthode pour fermer la connexion à la base de donns
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