package org.systems.dipe.srs.inventory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.inventory.storage.InventoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryClientImpl implements InventoryClient {

    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory create(Inventory inventory) {
        return inventoryRepository.create(inventory);
    }

    @Override
    public List<Inventory> search(InventorySearch search) {
        return inventoryRepository.search(search);
    }
}
