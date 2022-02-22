package org.systems.dipe.srs.location.storage;

import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.location.LocationsSearch;

import java.util.Collection;

public interface LocationsRepository {

    Location create(Location location);

    Location update(Location location);

    Collection<Location> search(LocationsSearch search);
}
