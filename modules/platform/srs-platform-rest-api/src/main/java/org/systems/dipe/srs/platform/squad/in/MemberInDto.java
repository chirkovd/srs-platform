package org.systems.dipe.srs.platform.squad.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInDto {
    private String memberId;
    private String volunteerId;
    private boolean head;
    private ZonedDateTime created;
}
