CREATE SCHEMA IF NOT EXISTS people;
SET search_path TO people;

CREATE TABLE IF NOT EXISTS person
(
    person_id       UUID            NOT NULL,

    first_name      VARCHAR(100)    NOT NULL,
    last_name       VARCHAR(100)    NOT NULL,

    created         TIMESTAMP       NOT NULL,

    CONSTRAINT "PK.people.person" PRIMARY KEY (person_id)
);

CREATE TABLE IF NOT EXISTS identification
(
    identity_id     VARCHAR(50)     NOT NULL,
    identity_type   VARCHAR(50)     NOT NULL,

    person_id       UUID            NOT NULL,

    created         TIMESTAMP       NOT NULL,

    CONSTRAINT "PK.people.identification" PRIMARY KEY (identity_id, identity_type),
    CONSTRAINT "FK.people.identification.person" FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE IF NOT EXISTS contact
(
    contact_id  UUID        NOT NULL,
    person_id   UUID        NOT NULL,

    phone       VARCHAR(100),
    email       VARCHAR(255),

    created     TIMESTAMP   NOT NULL,

    CONSTRAINT "PK.people.contact" PRIMARY KEY (contact_id),
    CONSTRAINT "FK.people.contact.person" FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE IF NOT EXISTS role
(
    role_id     UUID            NOT NULL,

    role        VARCHAR(100)    NOT NULL,
    description TEXT,
    created     TIMESTAMP       NOT NULL,

    CONSTRAINT "PK.people.role" PRIMARY KEY (role_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_people.role.role" ON role (role);

CREATE TABLE IF NOT EXISTS role_link
(
    role_id     UUID            NOT NULL,
    person_id   UUID            NOT NULL,

    created     TIMESTAMP       NOT NULL,

    CONSTRAINT "FK.people.role_link.role" FOREIGN KEY (role_id) REFERENCES role (role_id),
    CONSTRAINT "FK.people.role_link.person" FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_people.role_link.role_person" ON role_link (role_id, person_id);