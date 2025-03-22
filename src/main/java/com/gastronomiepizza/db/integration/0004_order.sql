CREATE TABLE IF NOT EXISTS "Order" (
                                       id_order VARCHAR PRIMARY KEY DEFAULT gen_random_uuid(),
                                       reference VARCHAR(255) NOT NULL UNIQUE,
                                       created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       status status NOT NULL DEFAULT 'CRÉÉ',
                                       last_status_change TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       dish_order_id VARCHAR(36) REFERENCES DishOrder(id) ON DELETE CASCADE
);