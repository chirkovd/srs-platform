package org.systems.dipe.srs.search;

public interface SearchProcessClient {

    SearchProcess create(SearchProcess process);

    SearchProcess update(SearchProcess process);

    void addSquad(SearchSquad squad);

    void addLocation(SearchLocation location);
}
