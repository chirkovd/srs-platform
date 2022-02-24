package org.systems.dipe.srs.search.storage;

import org.systems.dipe.srs.search.SearchLocation;
import org.systems.dipe.srs.search.SearchLocationsSearch;

import java.util.Collection;

public interface SearchLocationsRepository {

    void create(Collection<SearchLocation> locations);

    void update(Collection<SearchLocation> locations);

    Collection<SearchLocation> search(SearchLocationsSearch search);
}
