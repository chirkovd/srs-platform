package org.systems.dipe.srs.squad.storage;

import org.systems.dipe.srs.squad.Equipment;
import org.systems.dipe.srs.squad.EquipmentsSearch;

import java.util.Collection;

public interface EquipmentsRepository {

    void create(Collection<Equipment> equipments);

    void update(Collection<Equipment> equipments);

    Collection<Equipment> search(EquipmentsSearch search);

}
