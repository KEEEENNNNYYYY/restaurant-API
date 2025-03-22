CREATE TABLE DishOrder (
      id VARCHAR(36) PRIMARY KEY,
      order_id VARCHAR(36) REFERENCES "Order"(id_order) ON DELETE CASCADE,
      dish_id VARCHAR(36) NOT NULL,
      dish_quantity DOUBLE PRECISION NOT NULL,
      status status NOT NULL,
      last_status_change TIMESTAMP NOT NULL  ,
      CONSTRAINT fk_dish FOREIGN KEY (dish_id) REFERENCES dish(id_dish)
);