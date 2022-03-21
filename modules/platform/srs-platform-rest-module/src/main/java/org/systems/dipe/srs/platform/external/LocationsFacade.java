package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.out.LocationOutDto;

import java.util.List;
import java.util.Set;

public interface LocationsFacade {

    LocationOutDto create(LocationInDto location);

    List<LocationOutDto> search(Set<String> locationIds);
}
