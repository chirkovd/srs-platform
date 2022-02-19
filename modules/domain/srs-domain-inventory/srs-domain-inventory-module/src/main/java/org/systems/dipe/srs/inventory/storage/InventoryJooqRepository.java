package org.systems.dipe.srs.inventory.storage;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.inventory.Inventory;
import org.systems.dipe.srs.inventory.InventorySearch;

import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class InventoryJooqRepository implements InventoryRepository {

    private final DefaultDSLContext dsl;

    @Override
    public Inventory create(Inventory inventory) {
        return null;
    }

    @Override
    public List<Inventory> search(InventorySearch search) {
        return null;
    }
}
