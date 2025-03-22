package com.gastronomiepizza.DAO;

import com.gastronomiepizza.Database;
import com.gastronomiepizza.model.Dish;
import com.gastronomiepizza.model.DishOrder;
import com.gastronomiepizza.model.Status;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DishOrderDAO {
    public void saveDishOrderStatus(String dishOrderId, Status newStatus) throws SQLException {
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