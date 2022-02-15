package org.systems.dipe.srs.location;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LocationClientImpl implements LocationClient {
    @Override
    public Location create(Location location) {
        return null;
    }

    @Override
    public Location update(Location location) {
        return null;
    }

    @Override
    public void addPoints(Collection<Point> points) {

    }

    @Override
    public void addComments(Collection<Comment> comments) {

    }
}
