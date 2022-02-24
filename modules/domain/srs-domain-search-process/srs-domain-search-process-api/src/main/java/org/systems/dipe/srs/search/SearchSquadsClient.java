package org.systems.dipe.srs.search;

import java.util.Collection;

public interface SearchSquadsClient {

    void create(Collection<SearchSquad> squads);

    void update(Collection<SearchSquad> squads);

    Collection<SearchSquad> search(SearchSquadsSearch search);
}
