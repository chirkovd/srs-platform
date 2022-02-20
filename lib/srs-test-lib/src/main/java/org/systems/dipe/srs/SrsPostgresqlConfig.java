package org.systems.dipe.srs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SrsPostgresqlConfig {

    private static final String TEST_USER = "user";
    private static final String TEST_PASSWORD = "password";

    public static final PostgreSQLContainer<?> DB_CONTAINER =
            new PostgreSQLContainer<>("postgres:12.9-alpine")
                    .withDatabaseName("srs-platform-test")
                    .withUsername(TEST_USER)
                    .withPassword(TEST_PASSWORD);

    protected static class DbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + DB_CONTAINER.getJdbcUrl(),
                    "spring.datasource.username=" + TEST_USER,
                    "spring.datasource.password=" + TEST_PASSWORD,
                    "spring.datasource.driver-class-name=org.postgresql.Driver"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
