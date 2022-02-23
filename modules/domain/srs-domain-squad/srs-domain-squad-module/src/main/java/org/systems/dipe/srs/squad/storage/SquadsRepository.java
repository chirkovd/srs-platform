package org.systems.dipe.srs.squad.storage;

import org.systems.dipe.srs.squad.Squad;
import org.systems.dipe.srs.squad.SquadsSearch;

import java.util.Collection;

public interface SquadsRepository {

    void create(Squad squad);

    void update(Squad squad);

    Collection<Squad> search(SquadsSearch search);

}
