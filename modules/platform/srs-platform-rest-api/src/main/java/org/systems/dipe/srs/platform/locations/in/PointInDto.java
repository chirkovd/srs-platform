package org.systems.dipe.srs.platform.locations.in;

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
public final class PointInDto {
    private String pointId;
    private double longitude;
    private double latitude;
    private ZonedDateTime created;
    private List<CommentInDto> comments;
}
