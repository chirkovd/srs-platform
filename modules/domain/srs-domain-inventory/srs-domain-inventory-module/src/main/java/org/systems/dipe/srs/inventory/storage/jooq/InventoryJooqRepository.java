package org.systems.dipe.srs.inventory.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.inventory.Inventory;
import org.systems.dipe.srs.inventory.InventorySearch;
import org.systems.dipe.srs.inventory.jooq.tables.JInventory;
import org.systems.dipe.srs.inventory.storage.InventoryRepository;
import org.systems.dipe.srs.inventory.storage.mappers.InventoryMapper;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class InventoryJooqRepository implements InventoryRepository {

    private final DefaultDSLContext dsl;
    private final InventoryMapper mapper;

    @Override
    public void create(Inventory inventory) {
        dsl.insertInto(JInventory.INVENTORY)
                .set(mapper.toJooq(inventory))
                .execute();
    }

    @Override
    public List<Inventory> search(InventorySearch search) {
        return dsl.selectFrom(JInventory.INVENTORY)
                .where(JInventory.INVENTORY.INVENTORY_ID.in(UuidUtils.fromStr(search.getInventoryIds())))
                .fetch()
                .map(mapper::fromJooq);
    }
}
