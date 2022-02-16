package org.systems.dipe.srs.inventory;

import java.util.List;

public interface InventoryClient {

    Inventory create(Inventory inventory);

    List<Inventory> search(InventorySearch search);

}
