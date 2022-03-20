package org.systems.dipe.srs.test.suites.people;

import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.in.PointInDto;

import java.util.List;
import java.util.Random;

@Component
public class LocationProvider {

    public LocationInDto buildNewLocation() {
        LocationInDto location = new LocationInDto();
        PointInDto point = new PointInDto();
        Random random = new Random();
        point.setLongitude(random.nextDouble());
        point.setLatitude(random.nextDouble());
        location.setPoints(List.of(point));
        return location;
    }

}
