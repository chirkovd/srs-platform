package org.systems.dipe.srs.location;

import java.util.Collection;

public interface PointsClient {

    void create(Collection<Point> points);

    Collection<Point> search(PointsSearch search);
}
