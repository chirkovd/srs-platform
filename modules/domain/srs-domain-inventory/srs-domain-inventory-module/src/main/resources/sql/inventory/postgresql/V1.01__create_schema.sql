CREATE SCHEMA IF NOT EXISTS inventories;
SET search_path TO inventories;

CREATE TABLE IF NOT EXISTS inventory
(
    inventory_id    UUID            NOT NULL,
    name            VARCHAR(100)    NOT NULL,

    created  TIMESTAMP              NOT NULL DEFAULT now(),

    CONSTRAINT "PK.inventories.inventory" PRIMARY KEY (inventory_id)
);

