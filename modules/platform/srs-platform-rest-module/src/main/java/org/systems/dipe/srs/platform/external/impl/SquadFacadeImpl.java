package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.platform.external.PeopleFacade;
import org.systems.dipe.srs.platform.external.SquadFacade;
import org.systems.dipe.srs.platform.mappers.SquadsDtoMapper;
import org.systems.dipe.srs.platform.squad.out.SquadOutDto;
import org.systems.dipe.srs.squad.Member;
import org.systems.dipe.srs.squad.Squad;
import org.systems.dipe.srs.squad.SquadsClient;
import org.systems.dipe.srs.squad.SquadsSearch;
import org.systems.dipe.srs.utils.GroupUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Component("platformSquadFacade")
@ConditionalOnBean(SquadsClient.class)
public class SquadFacadeImpl implements SquadFacade {

    private final SquadsClient squadsClient;
    private final SquadsDtoMapper mapper;

    private final PeopleFacade peopleFacade;

    @Override
    public List<SquadOutDto> search(Set<String> squadIds) {
        Collection<Squad> squads = squadsClient.search(
                SquadsSearch.builder()
                        .squadIds(squadIds)
                        .withDetails(true)
                        .build());

        return squads.stream().map(squad -> {
            SquadOutDto squadOutDto = mapper.toDto(squad);

            //TODO set equipments
            squadOutDto.setEquipments(Collections.emptyList());

            Set<String> volunteerIds = GroupUtils.extractUnique(squad.getMembers(), Member::getVolunteerId);
            squadOutDto.setMembers(peopleFacade.search(volunteerIds));

            return squadOutDto;
        }).collect(Collectors.toList());
    }
}
