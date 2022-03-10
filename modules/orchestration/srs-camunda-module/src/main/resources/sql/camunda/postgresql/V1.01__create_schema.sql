CREATE SCHEMA IF NOT EXISTS orchestration;
SET search_path TO orchestration;

CREATE TABLE IF NOT EXISTS event
(
    event_id    SERIAL,
    
    status      VARCHAR(50) NOT NULL,

    message     JSONB       NOT NULL,
    error       TEXT,
    
    retry_at    TIMESTAMP,
    created     TIMESTAMP   NOT NULL,

    CONSTRAINT "PK.orchestration.event" PRIMARY KEY (event_id)
);

CREATE INDEX IF NOT EXISTS "IX_orchestration.event.status" ON event (status);
CREATE INDEX IF NOT EXISTS "IX_orchestration.event.retry" ON event (retry_at, status) WHERE retry_at IS NOT NULL;
