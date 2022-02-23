package org.systems.dipe.srs.squad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String memberId;
    private String squadId;
    private String volunteerId;
    private boolean head;
    private ZonedDateTime created;
}
