package org.systems.dipe.srs.location;

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
public final class Location {
    private String locationId;
    private ZonedDateTime created;
    private List<Point> points;
}
