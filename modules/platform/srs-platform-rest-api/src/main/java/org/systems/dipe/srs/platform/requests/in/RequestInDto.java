package org.systems.dipe.srs.platform.requests.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.people.in.PersonInDto;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestInDto {

    //TODO add validation annotations

    private String requestId;
    private String customerId;
    private String supervisorId;
    private ZonedDateTime created;
    private ZonedDateTime approved;
    private List<PersonInDto> people;
    private List<LocationInDto> locations;
}
