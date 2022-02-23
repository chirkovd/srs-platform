package org.systems.dipe.srs.squad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private String inventoryId;
    private String squadId;
    private ZonedDateTime created;
}
