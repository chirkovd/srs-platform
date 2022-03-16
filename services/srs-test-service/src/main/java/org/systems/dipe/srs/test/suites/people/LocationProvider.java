package org.systems.dipe.srs.test.suites.people;

import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;

@Component
public class LocationProvider {

    public LocationInDto buildNewLocation() {
        return new LocationInDto();
    }

}
