package com.gastronomiepizza;

import com.gastronomiepizza.DAO.DishDAO;
import com.gastronomiepizza.model.Criteria;
import com.gastronomiepizza.model.Dish;
import com.gastronomiepizza.model.DishIngredient;
import com.gastronomiepizza.model.Unit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GastronomiePizzaApplicationTests {
	Database dataBase = new Database();
	DishDAO dishDAO;
	private Connection connection;

	@BeforeEach
	public void setUp() throws SQLException {
		connection = dataBase.getConnection();
		dishDAO = new DishDAO();
	}

	@Test
	void testGetDishById_HotDogPrice() {
		LocalDate date = LocalDate.now();
		Dish hotDog = dishDAO.getDishById(1, date);

		// Vérification du prix
		assertEquals(5500.0, hotDog.getPrice(), "Le prix du Hot Dog doit être 5500.0");

		// Affichage des informations du plat
		System.out.println("Plat : " + hotDog.getName());
		System.out.println("Prix : " + hotDog.getPrice() + " Ar");
		System.out.println("Ingrédients : ");

		List<DishIngredient> ingredients = hotDog.getRecipe();
		for (DishIngredient ingredient : ingredients) {
			System.out.println("- " + ingredient.getName() + " : " + ingredient.getQuantity() + " " + ingredient.getUnit());
		}
	}
	@Test
	void testFilterSortPaginateIngredients() {
		Criteria criteria = new Criteria();
		criteria.setName("Tomate");
		criteria.setUnit(Unit.G);
		criteria.setMinPrice(1000.0);
		criteria.setMaxPrice(5000.0);
		criteria.setSortBy("unit_price");
		criteria.setSortOrder("ASC");
		criteria.setPage(1);
		criteria.setPageSize(5);

		List<DishIngredient> ingredients = dishDAO.filterSortPaginateIngredients(criteria);

		for (DishIngredient ingredient : ingredients) {
			assertTrue(ingredient.getName().contains("Tomate"), "Le nom de l'ingrédient doit contenir 'Tomate'");
			assertEquals(Unit.G, ingredient.getUnit(), "L'unité doit être en kilogramme");
			assertTrue(ingredient.getUnitPrice() >= 1000.0, "Le prix doit être supérieur ou égal à 1000.0");
			assertTrue(ingredient.getUnitPrice() <= 5000.0, "Le prix doit être inférieur ou égal à 5000.0");
		}

		System.out.println("Ingrédients filtrés et triés :");
		for (DishIngredient ingredient : ingredients) {
			System.out.println("- " + ingredient.getName() + " | " + ingredient.getUnit() + " | " + ingredient.getUnitPrice() + " Ar");
		}
	}

	@Test
	void testGetMakableDish_ExampleDish() {
		LocalDate date = LocalDate.now();

		Dish exampleDish = dishDAO.getDishById(1, date);
		assertNotNull(exampleDish, "Le plat exemple ne devrait pas être nul.");

		double makableDishes = dishDAO.getMakableDish(1);
		System.out.println(makableDishes);

		System.out.println("Nombre de plats réalisables : " + makableDishes);

		assertTrue(makableDishes >= 0, "Le nombre de plats réalisables doit être positif ou zéro.");

		if (makableDishes == 0) {
			System.out.println("Aucun plat réalisable. Vérifiez les stocks et les recettes.");
		}
	}




	@AfterEach
	void tearDown() throws Exception {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
}
