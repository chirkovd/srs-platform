package org.systems.dipe.srs.orchestration.external;

import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchProcessStatus;

public interface SearchProcessFacade {

    SearchProcess create(SearchProcess process);

    SearchProcess update(SearchProcess process);

    SearchProcess find(String searchId);

    void updateStatus(String searchId, SearchProcessStatus status);
}
