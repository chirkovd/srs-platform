package org.systems.dipe.srs.location.storage;

import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.location.LocationsSearch;

import java.util.Collection;

public interface LocationsRepository {

    void create(Location location);

    void update(Location location);

    Collection<Location> search(LocationsSearch search);
}
