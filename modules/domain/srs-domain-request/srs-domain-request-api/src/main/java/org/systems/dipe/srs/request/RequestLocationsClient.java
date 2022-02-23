package org.systems.dipe.srs.request;

import java.util.Collection;

public interface RequestLocationsClient {

    void create(Collection<RequestLocation> locations);

    void update(Collection<RequestLocation> locations);

    Collection<RequestLocation> search(RequestLocationsSearch search);
}
