package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Comment {
    private String commentId;
    private String pointId;
    private String authorId;
    private String comment;
    private ZonedDateTime created;
}
