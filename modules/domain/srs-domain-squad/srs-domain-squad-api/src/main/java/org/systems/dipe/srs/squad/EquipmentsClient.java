package org.systems.dipe.srs.squad;

import java.util.Collection;

public interface EquipmentsClient {

    void create(Collection<Equipment> equipments);

    void update(Collection<Equipment> equipments);

    Collection<Equipment> search(EquipmentsSearch search);
}
