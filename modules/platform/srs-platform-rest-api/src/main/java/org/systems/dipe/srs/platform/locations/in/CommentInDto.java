package org.systems.dipe.srs.platform.locations.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class CommentInDto {
    private String commentId;
    private String authorId;
    private String comment;
    private ZonedDateTime created;
}
