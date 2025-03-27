CREATE TABLE if not exists  "Ingredient" (
    id_ingredient varchar PRIMARY KEY default gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    unit Unit NOT NULL,
    update_datetime TIMESTAMP without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE Dish ADD CONSTRAINT unique_dish_price UNIQUE (id_dish, unit_price);
