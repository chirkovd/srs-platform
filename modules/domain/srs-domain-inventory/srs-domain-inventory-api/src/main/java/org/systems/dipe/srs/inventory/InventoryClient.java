package org.systems.dipe.srs.inventory;

import org.systems.dipe.SrsSingleClient;

import java.util.List;

public interface InventoryClient extends SrsSingleClient<Inventory> {

    List<Inventory> search(InventorySearch search);
}
