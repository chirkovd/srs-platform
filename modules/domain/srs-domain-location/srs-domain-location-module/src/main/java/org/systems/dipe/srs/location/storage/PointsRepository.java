package org.systems.dipe.srs.location.storage;

import org.systems.dipe.srs.location.Point;
import org.systems.dipe.srs.location.PointsSearch;

import java.util.Collection;

public interface PointsRepository {

    void create(Collection<Point> points);

    void update(Collection<Point> points);

    Collection<Point> search(PointsSearch search);

}
