package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.search.SearchProcessRequest;
import org.systems.dipe.srs.platform.search.out.SearchProcessOutDto;

public interface SearchProcessFacade {
    SearchProcessOutDto search(SearchProcessRequest request);
}
