package org.systems.dipe.srs.location;

import java.util.Collection;

public interface PointsClient {

    void create(Collection<Point> points);

    void update(Collection<Point> points);

    Collection<Point> search(PointsSearch search);
}
