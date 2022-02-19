package org.systems.dipe.srs.request.storage;

import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.request.RequestItem;
import org.systems.dipe.srs.request.RequestLocation;

import java.util.Collection;

public interface RequestRepository {
    Request create(Request request);

    Request update(Request request);

    void approve(String requestId);

    void addItems(Collection<RequestItem> items);

    void addLocations(Collection<RequestLocation> locations);
}
