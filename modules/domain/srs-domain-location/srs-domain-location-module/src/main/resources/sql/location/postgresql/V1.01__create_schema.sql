CREATE SCHEMA IF NOT EXISTS locations;
SET search_path TO locations;

CREATE TABLE IF NOT EXISTS location
(
    location_id UUID            NOT NULL,

    created     TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "PK.locations.location" PRIMARY KEY (location_id)
);

CREATE TABLE IF NOT EXISTS point
(
    point_id    UUID            NOT NULL,
    location_id UUID            NOT NULL,

    longitude   float8          NOT NULL,
    latitude    float8          NOT NULL,

    created     TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "PK.locations.point" PRIMARY KEY (point_id),
    CONSTRAINT "FK.locations.point.location" FOREIGN KEY (location_id) REFERENCES location (location_id)
);

CREATE TABLE IF NOT EXISTS comment
(
    comment_id  UUID            NOT NULL,
    point_id    UUID            NOT NULL,

    author_id   UUID          NOT NULL,
    comment     TEXT,

    created     TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "PK.locations.comment" PRIMARY KEY (comment_id),
    CONSTRAINT "FK.locations.comment.point" FOREIGN KEY (point_id) REFERENCES point (point_id)
);