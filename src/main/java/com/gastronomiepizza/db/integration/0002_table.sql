CREATE TABLE if not exists "Dish" (
id_dish varchar PRIMARY KEY default gen_random_uuid(),
name VARCHAR(255) NOT NULL,
unit_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE if not exists  "Ingredient" (
id_ingredient varchar PRIMARY KEY default gen_random_uuid(),
name VARCHAR(255) NOT NULL,
unit_price DECIMAL(10, 2) NOT NULL,
unit Unit NOT NULL,
update_datetime TIMESTAMP without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE Dish ADD CONSTRAINT unique_dish_price UNIQUE (id_dish, unit_price);


CREATE TABLE  if not exists "Dish_Ingredient" (
id_dish varchar PRIMARY KEY default gen_random_uuid(),
id_ingredient INT NOT NULL,
required_quantity DECIMAL(10, 2) NOT NULL,
unit Unit NOT NULL,
PRIMARY KEY (id_dish, id_ingredient),
FOREIGN KEY (id_dish) REFERENCES Dish(id_dish),
FOREIGN KEY (id_ingredient) REFERENCES Ingredient(id_ingredient)
);
