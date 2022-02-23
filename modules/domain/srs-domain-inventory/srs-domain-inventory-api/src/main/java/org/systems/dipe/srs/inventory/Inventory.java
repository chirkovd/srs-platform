package org.systems.dipe.srs.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Inventory {
    private String inventoryId;
    private String name;
    private ZonedDateTime created;
}
