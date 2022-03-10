package org.systems.dipe.srs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
public class SrsIntegrationTest {

    @Container
    static SrsPostgresqlContainer postgreSQLContainer = SrsPostgresqlContainer.getInstance();
    @Container
    static SrsRabbitMqContainer rabbitMqContainer = SrsRabbitMqContainer.getInstance();

    @BeforeEach
    void before() {
        log.info("Postgresql was started: {}", postgreSQLContainer.getJdbcUrl());
        log.info("RabbitMq was started: {}", rabbitMqContainer.getAmqpUrl());
    }
}
