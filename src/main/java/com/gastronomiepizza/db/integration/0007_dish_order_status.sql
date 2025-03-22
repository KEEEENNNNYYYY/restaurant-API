CREATE TABLE IF NOT EXISTS "DishOrderStatus" (
    id_order_status_history VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
    id_order VARCHAR NOT NULL,
    status status NOT NULL,
    changed_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_order) REFERENCES "DishOrder" (id)
);