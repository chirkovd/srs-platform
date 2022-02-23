package org.systems.dipe.srs.request.storage;

import org.systems.dipe.srs.request.RequestLocation;
import org.systems.dipe.srs.request.RequestLocationsSearch;

import java.util.Collection;

public interface RequestLocationsRepository {

    void create(Collection<RequestLocation> locations);

    void update(Collection<RequestLocation> locations);

    Collection<RequestLocation> search(RequestLocationsSearch search);
}
