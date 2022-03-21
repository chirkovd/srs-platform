package org.systems.dipe.srs.platform.squad.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberOutDto {
    private String memberId;
    private String volunteerId;
    private boolean head;
    private ZonedDateTime created;
}
