CREATE SCHEMA IF NOT EXISTS srs_person;
SET search_path TO srs_person;

CREATE TABLE IF NOT EXISTS person
(
    person_id       UUID            NOT NULL,
    username        VARCHAR(100)    NOT NULL,

    created  TIMESTAMP              NOT NULL DEFAULT now(),

    CONSTRAINT "PK.srs_person.person" PRIMARY KEY (person_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_srs_person.person.username" ON person (username);

CREATE TABLE IF NOT EXISTS contact
(
    contact_id  UUID        NOT NULL,
    person_id   UUID        NOT NULL,

    phone       VARCHAR(100),
    email       VARCHAR(255),
    created     TIMESTAMP   NOT NULL DEFAULT now(),

    CONSTRAINT "PK.srs_person.contact" PRIMARY KEY (contact_id),
    CONSTRAINT "FK.srs_person.contact.person" FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE IF NOT EXISTS role
(
    role_id     UUID            NOT NULL,

    role        VARCHAR(100)    NOT NULL,
    description TEXT,
    created     TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "PK.srs_person.role" PRIMARY KEY (role_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_srs_person.role.role" ON role (role);

CREATE TABLE IF NOT EXISTS role_link
(
    role_id     UUID            NOT NULL,
    person_id   UUID            NOT NULL,

    created     TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "FK.srs_person.role_link.role" FOREIGN KEY (role_id) REFERENCES role (role_id),
    CONSTRAINT "FK.srs_person.role_link.person" FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_srs_person.role_link.role_person" ON role_link (role_id, person_id);