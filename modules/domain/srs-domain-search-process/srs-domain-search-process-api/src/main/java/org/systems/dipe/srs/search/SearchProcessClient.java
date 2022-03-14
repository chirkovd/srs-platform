package org.systems.dipe.srs.search;

import java.util.Collection;

public interface SearchProcessClient {

    SearchProcess create(SearchProcess process);

    SearchProcess update(SearchProcess process);

    void updateStatus(String searchId, SearchProcessStatus status);

    Collection<SearchProcess> search(SearchProcessSearch search);
}
