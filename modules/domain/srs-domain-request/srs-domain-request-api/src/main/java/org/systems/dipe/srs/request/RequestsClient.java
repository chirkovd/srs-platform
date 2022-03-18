package org.systems.dipe.srs.request;

import java.util.Collection;

public interface RequestsClient {

    Request create(Request request);

    Request update(Request request);

    void assign(String requestId, String supervisorId);

    void approve(String requestId, String supervisorId);

    Collection<Request> search(RequestsSearch search);
}
