package org.systems.dipe.srs.search;

import java.util.Collection;

public interface SearchLocationsClient {

    void create(Collection<SearchLocation> locations);

    void update(Collection<SearchLocation> locations);

    Collection<SearchLocation> search(SearchLocationsSearch search);

}
