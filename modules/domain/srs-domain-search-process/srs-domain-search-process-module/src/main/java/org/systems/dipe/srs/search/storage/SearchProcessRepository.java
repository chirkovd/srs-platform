package org.systems.dipe.srs.search.storage;

import org.systems.dipe.srs.search.SearchLocation;
import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchSquad;

public interface SearchProcessRepository {
    SearchProcess create(SearchProcess process);

    SearchProcess update(SearchProcess process);

    void addSquad(SearchSquad squad);

    void addLocation(SearchLocation location);
}
