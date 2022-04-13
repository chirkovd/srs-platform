package org.systems.dipe.srs.inventory;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.metrics.SrsMetric;
import org.systems.dipe.metrics.SrsMetricHolder;
import org.systems.dipe.srs.inventory.storage.InventoryRepository;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
@SrsMetric("inventory")
public class InventoryClientImpl implements InventoryClient, SrsMetricHolder {

    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory create(Inventory inventory) {
        if (Objects.isNull(inventory.getInventoryId())) {
            inventory.setInventoryId(UuidUtils.newStr());
        }
        if (Objects.isNull(inventory.getCreated())) {
            inventory.setCreated(TimeUtils.now());
        }
        inventoryRepository.create(inventory);

        return find(inventory.getInventoryId());
    }

    @Override
    public List<Inventory> search(InventorySearch search) {
        if (CollectionUtils.isEmpty(search.getInventoryIds())) {
            return Collections.emptyList();
        }
        return inventoryRepository.search(search);
    }

    private Inventory find(String inventoryId) {
        List<Inventory> inventories = search(InventorySearch.builder()
                .inventoryIds(Set.of(inventoryId))
                .build());
        if (!inventories.isEmpty()) {
            return inventories.iterator().next();
        } else {
            log.error("Cannot find new inventory record by id {}", inventoryId);
            throw new IllegalArgumentException("Cannot find new inventory record");
        }
    }

    @Override
    public double initMetric() {
        return 10;
    }
}
