package org.systems.dipe.srs.squad;

import java.time.ZonedDateTime;

public record Member(String memberId, String squadId, String volunteerId, boolean head, ZonedDateTime created) {
}
