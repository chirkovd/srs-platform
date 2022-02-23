package org.systems.dipe.srs.squad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Squad {
    private String squadId;
    private ZonedDateTime created;
    private List<Equipment> equipments;
    private List<Member> members;
}
