package org.systems.dipe.srs.squad;

import java.util.Collection;

public interface SquadsClient {

    Squad create(Squad squad);

    Squad update(Squad squad);

    Collection<Squad> search(SquadsSearch search);

}
