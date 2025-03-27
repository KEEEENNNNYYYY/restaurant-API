CREATE TABLE  if not exists "Dish_Ingredient" (
    id_dish varchar PRIMARY KEY default gen_random_uuid(),
    id_ingredient INT NOT NULL,
    required_quantity DECIMAL(10, 2) NOT NULL,
    unit Unit NOT NULL,
    PRIMARY KEY (id_dish, id_ingredient),
    FOREIGN KEY (id_dish) REFERENCES Dish(id_dish),
    FOREIGN KEY (id_ingredient) REFERENCES Ingredient(id_ingredient)
);