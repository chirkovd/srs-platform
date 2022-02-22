package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.storage.LocationsRepository;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.*;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class LocationsClientImpl implements LocationsClient {

    private final LocationsRepository repository;
    private final PointsClient pointsClient;

    @Override
    public Location create(Location location) {
        if (Objects.isNull(location.getLocationId())) {
            location.setLocationId(UuidUtils.newStr());
        }
        if (Objects.isNull(location.getCreated())) {
            location.setCreated(TimeUtils.now());
        }
        repository.create(location);

        if (CollectionUtils.isNotEmpty(location.getPoints())) {
            for (Point point : location.getPoints()) {
                point.setLocationId(location.getLocationId());
            }
            pointsClient.create(location.getPoints());
        }

        return find(location.getLocationId());
    }

    @Override
    public Location update(Location location) {
        Objects.requireNonNull(location.getLocationId(), "Location id is required");
        repository.update(location);

        if (CollectionUtils.isNotEmpty(location.getPoints())) {
            for (Point point : location.getPoints()) {
                point.setLocationId(location.getLocationId());
            }
            pointsClient.update(location.getPoints());
        }

        return find(location.getLocationId());
    }

    @Override
    public Collection<Location> search(LocationsSearch search) {
        if (CollectionUtils.isEmpty(search.getLocationIds())) {
            return Collections.emptyList();
        }
        Collection<Location> locations = repository.search(search);
        if (CollectionUtils.isNotEmpty(locations) && search.isWithDetails()) {
            Collection<Point> points = pointsClient.search(
                    PointsSearch.builder()
                            .locationIds(GroupUtils.extractUnique(
                                    locations,
                                    Location::getLocationId
                            ))
                            .withDetails(true)
                            .build()
            );
            Map<String, List<Point>> pointsMap = GroupUtils.groupMultipleBy(points, Point::getLocationId);
            for (Location location : locations) {
                location.setPoints(pointsMap.get(location.getLocationId()));
            }
        }
        return locations;
    }

    private Location find(String locationId) {
        Collection<Location> locations = search(
                LocationsSearch.builder()
                        .locationIds(Set.of(locationId))
                        .withDetails(true)
                        .build()
        );
        if (!locations.isEmpty()) {
            return locations.iterator().next();
        } else {
            log.error("Cannot find new location by id {}", locationId);
            throw new IllegalStateException("Location was not stored");
        }
    }
}
