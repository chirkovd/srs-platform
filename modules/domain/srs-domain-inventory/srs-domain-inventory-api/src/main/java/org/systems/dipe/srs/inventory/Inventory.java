package org.systems.dipe.srs.inventory;

import java.time.ZonedDateTime;

public record Inventory(String inventoryId, String name, ZonedDateTime created) {

}
