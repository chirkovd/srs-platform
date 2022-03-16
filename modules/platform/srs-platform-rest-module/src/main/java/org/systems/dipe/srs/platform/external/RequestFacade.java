package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;

public interface RequestFacade {

    RequestOutDto submitRequest(RequestInDto request);

}
