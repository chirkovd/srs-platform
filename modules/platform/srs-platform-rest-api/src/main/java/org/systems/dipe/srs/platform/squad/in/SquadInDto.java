package org.systems.dipe.srs.platform.squad.in;

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
public class SquadInDto {
    private String squadId;
    private ZonedDateTime created;
    private List<EquipmentInDto> equipments;
    private List<MemberInDto> members;
}
