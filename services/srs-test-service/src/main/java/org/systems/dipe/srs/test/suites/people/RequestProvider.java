package org.systems.dipe.srs.test.suites.people;

import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;

import java.util.List;

@Component
public class RequestProvider {

    public RequestInDto buildNewRequest(String customerId, List<PersonInDto> target, List<LocationInDto> locations) {
        RequestInDto requestInDto = new RequestInDto();
        requestInDto.setCustomerId(customerId);
        requestInDto.setLocations(locations);
        requestInDto.setPeople(target);
        return requestInDto;
    }

}
