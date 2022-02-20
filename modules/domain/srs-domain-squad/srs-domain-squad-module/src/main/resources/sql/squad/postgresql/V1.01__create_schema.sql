CREATE SCHEMA IF NOT EXISTS squads;
SET search_path TO squads;

CREATE TABLE IF NOT EXISTS squad
(
    squad_id    UUID            NOT NULL,

    created     TIMESTAMP       NOT NULL,

    CONSTRAINT "PK.squads.squad" PRIMARY KEY (squad_id)
);

CREATE TABLE IF NOT EXISTS member
(
    member_id       UUID            NOT NULL,
    squad_id        UUID            NOT NULL,

    volunteer_id    UUID            NOT NULL,
    head            boolean         DEFAULT false,

    created         TIMESTAMP       NOT NULL,

    CONSTRAINT "PK.squads.member" PRIMARY KEY (member_id),
    CONSTRAINT "FK.squads.member.squad" FOREIGN KEY (squad_id) REFERENCES squad (squad_id)
);

CREATE TABLE IF NOT EXISTS equipment
(
    inventory_id    UUID            NOT NULL,
    squad_id        UUID            NOT NULL,

    created         TIMESTAMP       NOT NULL,

    CONSTRAINT "FK.squads.equipment.squad" FOREIGN KEY (squad_id) REFERENCES squad (squad_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_squads.equipment.inventory_squad" ON equipment (inventory_id, squad_id);



