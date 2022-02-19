package org.systems.dipe.srs.squad;

import java.time.ZonedDateTime;
import java.util.List;

public record Squad(String squadId, ZonedDateTime created, List<Equipment> equipments, List<Member> members) {
}
