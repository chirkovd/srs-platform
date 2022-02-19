CREATE SCHEMA IF NOT EXISTS requests;
SET search_path TO requests;

CREATE TABLE IF NOT EXISTS request
(
    request_id      UUID            NOT NULL,

    customer_id     UUID            NOT NULL,
    supervisor_id   UUID,

    approved        TIMESTAMP,
    created         TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "PK.requests.request" PRIMARY KEY (request_id)
);

CREATE INDEX IF NOT EXISTS "IX_requests.request.customer" ON request (customer_id);
CREATE INDEX IF NOT EXISTS "IX_requests.request.supervisor" ON request (supervisor_id) WHERE supervisor_id IS NOT NULL;

CREATE TABLE IF NOT EXISTS request_item
(
    item_id     UUID            NOT NULL,
    request_id  UUID            NOT NULL,

    target_id   UUID            NOT NULL,

    approved    TIMESTAMP,
    created     TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "PK.requests.request_item" PRIMARY KEY (item_id),
    CONSTRAINT "FK.requests.request_item.request_id" FOREIGN KEY (request_id) REFERENCES request (request_id)
);

CREATE TABLE IF NOT EXISTS request_location
(
    location_id UUID            NOT NULL,
    request_id  UUID            NOT NULL,

    created     TIMESTAMP       NOT NULL DEFAULT now(),

    CONSTRAINT "FK.requests.request_location.request_id" FOREIGN KEY (request_id) REFERENCES request (request_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS "IX_requests.request_location.location_request" ON request_location (location_id, request_id);