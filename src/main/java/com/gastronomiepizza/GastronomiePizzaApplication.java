package com.gastronomiepizza;

import com.gastronomiepizza.DAO.DishDAO;
import com.gastronomiepizza.DAO.IngredientDAO;
import com.gastronomiepizza.DAO.OrderDAO;
import com.gastronomiepizza.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GastronomiePizzaApplication {
	public static void main(String[] args) {
		/*try {
			// Créer une instance de OrderDAO
			OrderDAO orderDAO = new OrderDAO();

			// Créer une nouvelle commande
			Order order = new Order();
			order.setId("order12");
			order.setReference("REF12");
			order.setStatus(Status.CREATED);
			order.setOrderDate(LocalDate.now());
			order.setLastStatusChange(LocalDate.now());

			// Créer des plats (DishOrder) à ajouter à la commande
			DishDAO dishDAO = new DishDAO();

			// Récupérer un plat existant (supposons que le plat avec l'ID 1 existe)
			Dish dish1 = dishDAO.getDishById(1);
			Dish dish2 = dishDAO.getDishById(2);

			// Créer des DishOrder pour ces plats
			DishOrder dishOrder1 = new DishOrder();
			dishOrder1.setDish(dish1);
			dishOrder1.setDishId(dish1.getId().toString());
			dishOrder1.setDishQuantity(2.0);
			dishOrder1.setStatus(Status.CREATED);
			dishOrder1.setLastStatusChange(LocalDate.now());

			DishOrder dishOrder2 = new DishOrder();
			dishOrder2.setDish(dish2);
			dishOrder2.setDishId(dish2.getId().toString());
			dishOrder2.setDishQuantity(1.0);
			dishOrder2.setStatus(Status.CREATED);
			dishOrder2.setLastStatusChange(LocalDate.now());

			// Ajouter les plats à la commande
			List<DishOrder> dishOrders = new ArrayList<>();
			dishOrders.add(dishOrder1);
			dishOrders.add(dishOrder2);
			order.setDishOrders(dishOrders);

			// Créer la commande dans la base de données
			orderDAO.createOrder(order);

			// Ajouter les plats à la commande dans la base de données
			orderDAO.addDishToOrder(order.getId(), dishOrder1);
			orderDAO.addDishToOrder(order.getId(), dishOrder2);

			// Récupérer la commande depuis la base de données
			Order retrievedOrder = orderDAO.getOrderById(order.getId());
			System.out.println("Commande récupérée :");
			System.out.println("ID : " + retrievedOrder.getId());
			System.out.println("Référence : " + retrievedOrder.getReference());
			System.out.println("Statut : " + retrievedOrder.getStatus());
			System.out.println("Montant total : " + retrievedOrder.getTotalAmount());

			// Afficher les plats de la commande
			System.out.println("Plats dans la commande :");
			for (DishOrder dishOrder : retrievedOrder.getDishOrders()) {
				System.out.println(" - Plat ID : " + dishOrder.getDishId());
				System.out.println("   Nom : " + dishOrder.getDish().getName());
				System.out.println("   Quantité : " + dishOrder.getDishQuantity());
				System.out.println("   Statut : " + dishOrder.getStatus());
			}

			// Mettre à jour le statut de la commande
			orderDAO.updateOrderStatus(order.getId(), Status.CONFIRMED);

			// Récupérer la commande mise à jour
			Order updatedOrder = orderDAO.getOrderById(order.getId());
			System.out.println("Commande mise à jour :");
			System.out.println("Nouveau statut : " + updatedOrder.getStatus());

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erreur lors de l'exécution des opérations sur la base de données.");
		}*/
	}
}
