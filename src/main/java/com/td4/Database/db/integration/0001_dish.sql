CREATE TABLE if not exists "Dish" (
    id_dish varchar PRIMARY KEY default gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL
);
