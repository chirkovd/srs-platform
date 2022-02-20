package org.systems.dipe.srs;

import org.testcontainers.containers.PostgreSQLContainer;

public class SrsPostgresqlContainer extends PostgreSQLContainer<SrsPostgresqlContainer> {

    private static final String IMAGE_VERSION = "postgres:12.9-alpine";
    private static final String TEST_USER = "user";
    private static final String TEST_PASSWORD = "password";

    private static SrsPostgresqlContainer container;

    private SrsPostgresqlContainer() {
        super(IMAGE_VERSION);

        withDatabaseName("srs-platform-test")
                .withUsername(TEST_USER)
                .withPassword(TEST_PASSWORD);
    }

    public static SrsPostgresqlContainer getInstance() {
        if (container == null) {
            container = new SrsPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("SRS_DB_URL", container.getJdbcUrl());
        System.setProperty("SRS_DB_USERNAME", container.getUsername());
        System.setProperty("SRS_DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

}
