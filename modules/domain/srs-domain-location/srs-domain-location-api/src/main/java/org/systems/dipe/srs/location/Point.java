package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class Point {
    private String pointId;
    private String locationId;
    private double longitude;
    private double latitude;
    private ZonedDateTime created;
    private List<Comment> comments;
}
