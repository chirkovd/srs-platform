package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.out.LocationOutDto;

public interface LocationsFacade {

    LocationOutDto create(LocationInDto location);

}
