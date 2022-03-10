package org.systems.dipe.srs.orchestration.events;

public class RetryEventException extends RuntimeException {

    private static final long serialVersionUID = -3953042378510998156L;

    public RetryEventException(String message) {
        super(message);
    }
}
