CREATE SCHEMA IF NOT EXISTS srs_people;
SET search_path TO srs_people;

CREATE TABLE IF NOT EXISTS person
(
    person_id       UUID            NOT NULL,
    username        VARCHAR(100)    NOT NULL,

    created  TIMESTAMP              NOT NULL DEFAULT now(),

    CONSTRAINT "PK.srs_people.person" PRIMARY KEY (person_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_srs_people.person.username" ON person (username);

CREATE TABLE IF NOT EXISTS contact
(
    contact_id  UUID        NOT NULL,
    person_id   UUID        NOT NULL,

    phone       VARCHAR(100),
    email       VARCHAR(255),
    created     TIMESTAMP   NOT NULL DEFAULT now(),

    CONSTRAINT "PK.srs_people.contact" PRIMARY KEY (contact_id),
    CONSTRAINT "FK.srs_people.contact.person" FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE IF NOT EXISTS role
(
    role_id     UUID            NOT NULL,

    role        VARCHAR(100)    NOT NULL,
    description TEXT,
    created     TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "PK.srs_people.role" PRIMARY KEY (role_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_srs_people.role.role" ON role (role);

CREATE TABLE IF NOT EXISTS role_link
(
    role_id     UUID            NOT NULL,
    person_id   UUID            NOT NULL,

    created     TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "FK.srs_people.role_link.role" FOREIGN KEY (role_id) REFERENCES role (role_id),
    CONSTRAINT "FK.srs_people.role_link.person" FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_srs_people.role_link.role_person" ON role_link (role_id, person_id);