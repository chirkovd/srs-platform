package org.systems.dipe.srs.location;

import java.util.Collection;

public interface LocationClient {

    Location create(Location location);

    Location update(Location location);

    Collection<Location> search(LocationSearch search);

    void addPoints(Collection<Point> points);

    void addComments(Collection<Comment> comments);

}
