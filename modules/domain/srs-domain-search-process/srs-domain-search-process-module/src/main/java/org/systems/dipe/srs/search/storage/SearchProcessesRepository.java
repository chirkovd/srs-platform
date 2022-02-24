package org.systems.dipe.srs.search.storage;

import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchProcessSearch;

import java.util.Collection;

public interface SearchProcessesRepository {

    void create(SearchProcess process);

    void update(SearchProcess process);

    Collection<SearchProcess> search(SearchProcessSearch search);
}
