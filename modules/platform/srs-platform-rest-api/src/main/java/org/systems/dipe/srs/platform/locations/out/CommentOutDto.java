package org.systems.dipe.srs.platform.locations.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class CommentOutDto {
    private String commentId;
    private String pointId;
    private String authorId;
    private String comment;
    private ZonedDateTime created;
}
