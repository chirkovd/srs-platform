package org.systems.dipe.srs.search.storage;

import org.systems.dipe.srs.search.SearchSquad;
import org.systems.dipe.srs.search.SearchSquadsSearch;

import java.util.Collection;

public interface SearchSquadsRepository {

    void create(Collection<SearchSquad> squads);

    void update(Collection<SearchSquad> squads);

    Collection<SearchSquad> search(SearchSquadsSearch search);

}
