package org.systems.dipe.srs.request;

import java.util.Collection;

public interface RequestsClient {

    Request create(Request request);

    Request update(Request request);

    void approve(String requestId);

    Collection<Request> search(RequestsSearch search);
}
