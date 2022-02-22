package org.systems.dipe.srs.location;

import java.util.Collection;

public interface LocationsClient {

    Location create(Location location);

    Location update(Location location);

    Collection<Location> search(LocationsSearch search);

}
