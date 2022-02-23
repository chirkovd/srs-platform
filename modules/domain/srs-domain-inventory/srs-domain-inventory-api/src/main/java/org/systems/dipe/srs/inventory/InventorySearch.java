package org.systems.dipe.srs.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public final class InventorySearch {
    private final Set<String> inventoryIds;
}
