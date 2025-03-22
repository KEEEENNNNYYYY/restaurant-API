package com.gastronomiepizza;

import com.gastronomiepizza.DAO.DishDAO;
import com.gastronomiepizza.DAO.OrderDAO;
import com.gastronomiepizza.model.Dish;
import com.gastronomiepizza.model.DishOrder;
import com.gastronomiepizza.model.Order;
import com.gastronomiepizza.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GastronomiePizzaApplicationTests{

    private OrderDAO orderDAO;
    private DishDAO dishDAO;

    @BeforeEach
    public void setUp() {
        orderDAO = new OrderDAO();
        dishDAO = new DishDAO();
    }

    @Test
    public void createOrderTest() throws SQLException {
        // Créer une nouvelle commande
        Order order = orderDAO.getOrderById("order12");


        //System.out.println("order : " + order);

        //créer des dishOrders :
        Dish dish1 = dishDAO.getDishById(1);//hot dog;
        //transformation de dish1 en dishOrder1
        DishOrder dishOrder1 = new DishOrder();
        dishOrder1.setDish(dish1);
        dishOrder1.setDishId(dish1.getId());
        dishOrder1.setDishQuantity(5.0);
        dishOrder1.setStatus(Status.CREATED);
        dishOrder1.setLastStatusChange(LocalDate.now());

        //System.out.println("dish1 : " + dish1);
        //System.out.println("dishOrder1 :" + dishOrder1);

        //creation de dish2
        Dish dish2 = dishDAO.getDishById(2);//Pizza Pepperoni;
        //transformation de dish2 en dishOrder2
        DishOrder dishOrder2 = new DishOrder();
        dishOrder2.setDish(dish2);
        dishOrder2.setDishId(dish2.getId());
        dishOrder2.setDishQuantity(1.0);
        dishOrder2.setStatus(Status.CREATED);
        dishOrder2.setLastStatusChange(LocalDate.now());

        //System.out.println("dish2 : " + dish2);
        //System.out.println("dishOrder2 :" + dishOrder2);

        //ajout de dishOrder dans order
        order.setDishOrders(List.of(dishOrder1, dishOrder2));
        /*System.out.println("order :" + order.getTotalAmount()); //OK
        System.out.println("order : "+order.getDishOrders()); // OK
        System.out.println("order : "+ order.getActualStatus()); // OK*/

        //actual test :
        assertNotNull(order);
        assertNotNull(dishOrder1);
        assertNotNull(dishOrder2);
        assertEquals(2, order.getDishOrders().size());
    }
    @Test
    public void testGetTotalAmountTESt() throws SQLException {
        // Créer une nouvelle commande
        Order order = orderDAO.getOrderById("order12");

        //System.out.println("order : " + order);

        //créer des dishOrders :
        Dish dish1 = dishDAO.getDishById(1);//hot dog;
        //transformation de dish1 en dishOrder1
        DishOrder dishOrder1 = new DishOrder();
        dishOrder1.setDish(dish1);
        dishOrder1.setDishId(dish1.getId());
        dishOrder1.setDishQuantity(5.0);
        dishOrder1.setStatus(Status.CREATED);
        dishOrder1.setLastStatusChange(LocalDate.now());

        //System.out.println("dish1 : " + dish1);
        //System.out.println("dishOrder1 :" + dishOrder1);

        //creation de dish2
        Dish dish2 = dishDAO.getDishById(2);//Pizza Pepperoni;
        //transformation de dish2 en dishOrder2
        DishOrder dishOrder2 = new DishOrder();
        dishOrder2.setDish(dish2);
        dishOrder2.setDishId(dish2.getId());
        dishOrder2.setDishQuantity(1.0);
        dishOrder2.setStatus(Status.CREATED);
        dishOrder2.setLastStatusChange(LocalDate.now());

        //System.out.println("dish2 : " + dish2);
        //System.out.println("dishOrder2 :" + dishOrder2);

        //ajout de dishOrder dans order
        order.setDishOrders(List.of(dishOrder1, dishOrder2));

        // Vérifier le montant total de la commande
        double expected =(15000.0*5.0) + (12);
        double actual = order.getTotalAmount();


        //actual test :
        assertNotNull(order);
        assertNotNull(dishOrder1);
        assertNotNull(dishOrder2);
        assertEquals(2, order.getDishOrders().size());
        assertEquals(expected,actual);
    }
    @Test
    public void getDishOrderTest() throws SQLException {
        // Créer une nouvelle commande
        Order order = orderDAO.getOrderById("order12");

        //System.out.println("order : " + order);

        //créer des dishOrders :
        Dish dish1 = dishDAO.getDishById(1);//hot dog;
        //transformation de dish1 en dishOrder1
        DishOrder dishOrder1 = new DishOrder();
        dishOrder1.setDish(dish1);
        dishOrder1.setDishId(dish1.getId());
        dishOrder1.setDishQuantity(5.0);
        dishOrder1.setStatus(Status.CREATED);
        dishOrder1.setLastStatusChange(LocalDate.now());

        //System.out.println("dish1 : " + dish1);
        //System.out.println("dishOrder1 :" + dishOrder1);

        //creation de dish2
        Dish dish2 = dishDAO.getDishById(2);//Pizza Pepperoni;
        //transformation de dish2 en dishOrder2
        DishOrder dishOrder2 = new DishOrder();
        dishOrder2.setDish(dish2);
        dishOrder2.setDishId(dish2.getId());
        dishOrder2.setDishQuantity(1.0);
        dishOrder2.setStatus(Status.CREATED);
        dishOrder2.setLastStatusChange(LocalDate.now());

        //System.out.println("dish2 : " + dish2);
        //System.out.println("dishOrder2 :" + dishOrder2);

        //ajout de dishOrder dans order
        order.setDishOrders(List.of(dishOrder1, dishOrder2));

        //actual test :
        assertNotNull(order);
        assertNotNull(dishOrder1);
        assertNotNull(dishOrder2);
        assertEquals(2, order.getDishOrders().size());
        assertEquals(dishOrder1, order.getDishOrders().get(0));
        assertEquals(dishOrder2, order.getDishOrders().get(1));
    }
    @Test
    public void getActualStatusTest() throws SQLException {
        // Créer une nouvelle commande
        Order order = orderDAO.getOrderById("order123");

        //System.out.println("order : " + order);

        //créer des dishOrders :
        Dish dish1 = dishDAO.getDishById(1);//hot dog;
        //transformation de dish1 en dishOrder1
        DishOrder dishOrder1 = new DishOrder();
        dishOrder1.setDish(dish1);
        dishOrder1.setDishId(dish1.getId());
        dishOrder1.setDishQuantity(5.0);
        dishOrder1.setStatus(Status.CREATED);
        dishOrder1.setLastStatusChange(LocalDate.now());

        //System.out.println("dish1 : " + dish1);
        //System.out.println("dishOrder1 :" + dishOrder1);

        //creation de dish2
        Dish dish2 = dishDAO.getDishById(2);//Pizza Pepperoni;
        //transformation de dish2 en dishOrder2
        DishOrder dishOrder2 = new DishOrder();
        dishOrder2.setDish(dish2);
        dishOrder2.setDishId(dish2.getId());
        dishOrder2.setDishQuantity(1.0);
        dishOrder2.setStatus(Status.CREATED);
        dishOrder2.setLastStatusChange(LocalDate.now());

        //System.out.println("dish2 : " + dish2);
        //System.out.println("dishOrder2 :" + dishOrder2);

        //ajout de dishOrder dans order
        order.setDishOrders(List.of(dishOrder1, dishOrder2));

        //actual test :
        assertNotNull(order);
        assertNotNull(dishOrder1);
        assertNotNull(dishOrder2);
        assertEquals(2, order.getDishOrders().size());
        System.out.println(order.getStatus());
        System.out.println(dishOrder1.getStatus());
        System.out.println(dishOrder2.getActualStatus());

        assertEquals(Status.CREATED, order.getStatus());
        assertEquals(Status.CREATED, dishOrder1.getStatus());
        assertEquals(Status.CREATED, dishOrder2.getStatus());

        System.out.println("order list status :" + order.getOrderListStatus());

        order.setStatus(Status.ON_COOCKING);
        dishOrder1.setStatus(Status.ON_COOCKING);
        dishOrder2.setStatus(Status.ON_COOCKING);

        System.out.println("order list status :" + order.getOrderListStatus());

        assertEquals(Status.ON_COOCKING, order.getStatus());
        assertEquals(Status.ON_COOCKING, dishOrder1.getStatus());
        assertEquals(Status.ON_COOCKING, dishOrder2.getStatus());
        assertEquals(List.of(Status.ON_COOCKING, Status.ON_COOCKING), order.getOrderListStatus());

        order.statusChange();
        assertEquals(Status.DONE, order.getActualStatus());
        assertEquals(Status.DONE, dishOrder1.getActualStatus());
        assertEquals(Status.DONE, dishOrder2.getActualStatus());
        orderDAO.saveOrder(order.getId(), order.getStatus());

        //save method
        orderDAO.saveOrder(order.getId(), order.getActualStatus());
        orderDAO.saveDishOrder(dishOrder1.getDishOrderId(), dishOrder1.getActualStatus());
        orderDAO.saveDishOrder(dishOrder2.getDishOrderId(), dishOrder2.getActualStatus());


    }
    @Test
    public void testUnmakeableDishNotAdded() throws SQLException {
        // Créer une nouvelle commande
        Order order = new Order();
        order.setId("order13");
        order.setReference("REF13");
        order.setStatus(Status.CREATED);
        order.setOrderDate(LocalDate.now());
        order.setLastStatusChange(LocalDate.now());

        // Créer des plats (DishOrder) à ajouter à la commande
        Dish dish1 = dishDAO.getDishById(1); // hot dog
        Dish dish2 = dishDAO.getDishById(2); // Pizza Pepperoni

        // Créer des DishOrder pour ces plats
        DishOrder dishOrder1 = new DishOrder();
        dishOrder1.setDish(dish1);
        dishOrder1.setDishId(dish1.getId());
        dishOrder1.setDishQuantity(2.0);
        dishOrder1.setStatus(Status.CREATED);
        dishOrder1.setLastStatusChange(LocalDate.now());
        dishOrder1.setMakeable(true);

        DishOrder dishOrder2 = new DishOrder();
        dishOrder2.setDish(dish2);
        dishOrder2.setDishId(dish2.getId());
        dishOrder2.setDishQuantity(1.0);
        dishOrder2.setStatus(Status.CREATED);
        dishOrder2.setLastStatusChange(LocalDate.now());
        dishOrder2.setMakeable(false);

        // Ajouter les plats à la commande
        List<DishOrder> dishOrders = new ArrayList<>();
        dishOrders.add(dishOrder1);
        dishOrders.add(dishOrder2);

        order.setDishOrders(dishOrders);

        // actual test
        assertEquals(1, order.getDishOrders().size());
        assertEquals(dishOrder1, order.getDishOrders().get(0));

        System.out.println(order.getDishOrder());
    }
    @Test
    public void testSaveOrderAndDishOrders() throws SQLException {
        String orderId = "order13";
        Order order = orderDAO.getOrderById(orderId);
        assertNotNull(order);
        
        orderDAO.saveOrder(order.getId(),order.getActualStatus());
        
        Order order1 = orderDAO.getOrderById("order13");

        /**
         * comparaison si le nouvel objet tiré de la base de donné apres changement est 
         * identique à l'object avant sa sauvegarde 
         */
        assertEquals(Status.CONFIRMED, order1.getStatus());
        assertEquals(order.getStatus(), order1.getStatus());
        
        DishOrder dishOrder = orderDAO.getDishById("1");
        assertNotNull(dishOrder);

        orderDAO.saveDishOrder(dishOrder.getId(),dishOrder.getActualStatus());

        DishOrder dishOrder1 = orderDAO.getDishById("1");

        /**
         * comparaison si le nouvel DishOrder tiré de la base de donné apres changement est 
         * identique au DishOrder avant sa sauvegarde 
         */
        assertEquals(Status.CONFIRMED, dishOrder1.getStatus());
        assertEquals(dishOrder.getStatus(), dishOrder1.getStatus());
    }
}
