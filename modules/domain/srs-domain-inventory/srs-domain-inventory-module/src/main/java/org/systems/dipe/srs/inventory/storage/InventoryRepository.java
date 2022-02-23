package org.systems.dipe.srs.inventory.storage;

import org.systems.dipe.srs.inventory.Inventory;
import org.systems.dipe.srs.inventory.InventorySearch;

import java.util.List;

public interface InventoryRepository {

    void create(Inventory inventory);

    List<Inventory> search(InventorySearch search);
}
