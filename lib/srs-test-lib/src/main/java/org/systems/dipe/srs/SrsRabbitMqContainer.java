package org.systems.dipe.srs;

import org.testcontainers.containers.RabbitMQContainer;

public class SrsRabbitMqContainer extends RabbitMQContainer {

    private static final String IMAGE_VERSION = "rabbitmq:3.9.13-alpine";
    private static final String TEST_USER = "user";
    private static final String TEST_PASSWORD = "password";

    private static SrsRabbitMqContainer container;

    private SrsRabbitMqContainer() {
        super(IMAGE_VERSION);

        withUser(TEST_USER, TEST_PASSWORD)
                .withPermission("/", TEST_USER, ".*", ".*", ".*");
    }

    public static SrsRabbitMqContainer getInstance() {
        if (container == null) {
            container = new SrsRabbitMqContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("SRS_RABBITMQ_HOST", container.getHost());
        System.setProperty("SRS_RABBITMQ_PORT", String.valueOf(container.getAmqpPort()));
        System.setProperty("SRS_RABBITMQ_USERNAME", container.getAdminUsername());
        System.setProperty("SRS_RABBITMQ_PASSWORD", container.getAdminPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

}
