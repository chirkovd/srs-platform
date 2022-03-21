package org.systems.dipe.srs.platform.squad.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;

import java.time.ZonedDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SquadOutDto {
    private String squadId;
    private ZonedDateTime created;
    private List<EquipmentOutDto> equipments;
    private List<PersonOutDto> members;
}
