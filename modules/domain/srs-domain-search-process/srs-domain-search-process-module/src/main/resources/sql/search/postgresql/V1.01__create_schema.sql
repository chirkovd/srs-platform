CREATE SCHEMA IF NOT EXISTS searches;
SET search_path TO searches;

CREATE TABLE IF NOT EXISTS search_process
(
    process_id      UUID            NOT NULL,

    request_id      UUID            NOT NULL,
    status          VARCHAR(50)     NOT NULL,

    created         TIMESTAMP       NOT NULL,

    CONSTRAINT "PK.searches.search_process" PRIMARY KEY (process_id)
);

CREATE TABLE IF NOT EXISTS search_location
(
    location_id    UUID            NOT NULL,
    process_id     UUID            NOT NULL,

    created        TIMESTAMP       NOT NULL,

    CONSTRAINT "PK.searches.search_location" PRIMARY KEY (location_id),
    CONSTRAINT "FK.searches.search_location.process" FOREIGN KEY (process_id) REFERENCES search_process (process_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_searches.search_location.location_process" ON search_location (location_id, process_id);

CREATE TABLE IF NOT EXISTS search_squad
(
    squad_id       UUID            NOT NULL,
    process_id     UUID            NOT NULL,

    created        TIMESTAMP       NOT NULL,

    CONSTRAINT "PK.searches.search_squad" PRIMARY KEY (squad_id),
    CONSTRAINT "FK.searches.search_squad.process" FOREIGN KEY (process_id) REFERENCES search_process (process_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_searches.search_squad.squad_process" ON search_squad (squad_id, process_id);


