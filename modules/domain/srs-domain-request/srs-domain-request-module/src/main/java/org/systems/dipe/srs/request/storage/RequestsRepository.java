package org.systems.dipe.srs.request.storage;

import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.request.RequestsSearch;

import java.util.Collection;

public interface RequestsRepository {
    void create(Request request);

    void update(Request request);

    void approve(String requestId);

    Collection<Request> search(RequestsSearch search);
}
