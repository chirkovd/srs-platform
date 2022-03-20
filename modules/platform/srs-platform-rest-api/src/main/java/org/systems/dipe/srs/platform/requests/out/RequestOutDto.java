package org.systems.dipe.srs.platform.requests.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.systems.dipe.srs.platform.locations.out.LocationOutDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestOutDto {
    private String requestId;
    private String customerId;
    private String supervisorId;
    private ZonedDateTime created;
    private ZonedDateTime approved;
    private Set<String> itemIds;
    private List<PersonOutDto> people;
    private List<LocationOutDto> locations;
}
