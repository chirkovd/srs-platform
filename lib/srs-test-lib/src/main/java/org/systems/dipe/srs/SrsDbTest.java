package org.systems.dipe.srs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
@ContextConfiguration(initializers = SrsPostgresqlConfig.DbInitializer.class)
public class SrsDbTest {

    @Container
    public static PostgreSQLContainer<?> dbContainer = SrsPostgresqlConfig.DB_CONTAINER;

    @BeforeEach
    void setUp() {
        log.info("Postgresql was started: {}", dbContainer.getJdbcUrl());
    }
}
