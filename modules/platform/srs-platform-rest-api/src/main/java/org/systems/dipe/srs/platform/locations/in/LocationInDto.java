package org.systems.dipe.srs.platform.locations.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class LocationInDto {
    private String locationId;
    private ZonedDateTime created;
    private List<PointInDto> points;
}
