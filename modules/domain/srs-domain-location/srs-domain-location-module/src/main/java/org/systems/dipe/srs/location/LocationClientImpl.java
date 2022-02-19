package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.location.storage.LocationRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class LocationClientImpl implements LocationClient {

    private final LocationRepository locationRepository;

    @Override
    public Location create(Location location) {
        return locationRepository.create(location);
    }

    @Override
    public Location update(Location location) {
        return locationRepository.update(location);
    }

    @Override
    public Collection<Location> search(LocationSearch search) {
        return locationRepository.search(search);
    }

    @Override
    public void addPoints(Collection<Point> points) {
        locationRepository.addPoints(points);
    }

    @Override
    public void addComments(Collection<Comment> comments) {
        locationRepository.addComments(comments);
    }
}
