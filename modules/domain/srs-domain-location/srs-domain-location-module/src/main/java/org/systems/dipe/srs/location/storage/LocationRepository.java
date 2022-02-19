package org.systems.dipe.srs.location.storage;

import org.systems.dipe.srs.location.Comment;
import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.location.LocationSearch;
import org.systems.dipe.srs.location.Point;

import java.util.Collection;

public interface LocationRepository {

    Location create(Location location);

    Location update(Location location);

    Collection<Location> search(LocationSearch search);

    void addPoints(Collection<Point> points);

    void addComments(Collection<Comment> comments);
}
