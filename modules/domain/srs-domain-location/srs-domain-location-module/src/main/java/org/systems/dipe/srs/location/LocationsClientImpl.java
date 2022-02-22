package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.storage.LocationsRepository;

import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
public class LocationsClientImpl implements LocationsClient {

    private final LocationsRepository locationsRepository;

    @Override
    public Location create(Location location) {
        return locationsRepository.create(location);
    }

    @Override
    public Location update(Location location) {
        return locationsRepository.update(location);
    }

    @Override
    public Collection<Location> search(LocationsSearch search) {
        return locationsRepository.search(search);
    }
}
