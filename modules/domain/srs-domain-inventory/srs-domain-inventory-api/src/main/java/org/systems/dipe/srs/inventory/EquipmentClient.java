package org.systems.dipe.srs.inventory;

import java.util.List;

public interface EquipmentClient {

    Equipment create(Equipment equipment);

    List<Equipment> search(EquipmentSearch search);

}
