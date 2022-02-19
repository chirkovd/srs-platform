package org.systems.dipe.srs.request;

import java.util.Collection;

public interface RequestClient {

    Request create(Request request);

    Request update(Request request);

    void approve(String requestId);

    void addItems(Collection<RequestItem> items);

    void addLocations(Collection<RequestLocation> locations);

}
