package org.systems.dipe.srs.platform.squad.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentOutDto {
    private String inventoryId;
    private ZonedDateTime created;
}
