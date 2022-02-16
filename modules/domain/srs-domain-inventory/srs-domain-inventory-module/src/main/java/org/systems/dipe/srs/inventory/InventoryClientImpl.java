package org.systems.dipe.srs.inventory;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class InventoryClientImpl implements InventoryClient {

    @Override
    public Inventory create(Inventory inventory) {
        return null;
    }

    @Override
    public List<Inventory> search(InventorySearch search) {
        return Collections.emptyList();
    }
}
