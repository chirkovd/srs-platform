package org.systems.dipe.srs.platform.search.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.systems.dipe.srs.platform.locations.out.LocationOutDto;
import org.systems.dipe.srs.platform.squad.out.SquadOutDto;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchProcessOutDto {
    private String searchId;
    private String requestId;
    private String status;
    private ZonedDateTime created;
    private List<LocationOutDto> locations;
    private List<SquadOutDto> squads;
}
