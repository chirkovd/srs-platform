package org.systems.dipe.srs.squad;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.squad.storage.SquadsRepository;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class SquadsClientImpl implements SquadsClient {

    private final SquadsRepository repository;
    private final MembersClient membersClient;
    private final EquipmentsClient equipmentsClient;

    @Override
    public Squad create(Squad squad) {
        if (Objects.isNull(squad.getSquadId())) {
            squad.setSquadId(UuidUtils.newStr());
        }
        if (Objects.isNull(squad.getCreated())) {
            squad.setCreated(TimeUtils.now());
        }
        repository.create(squad);
        if (CollectionUtils.isNotEmpty(squad.getMembers())) {
            for (Member member : squad.getMembers()) {
                member.setSquadId(squad.getSquadId());
            }
            membersClient.create(squad.getMembers());
        }
        if (CollectionUtils.isNotEmpty(squad.getEquipments())) {
            for (Equipment equipment : squad.getEquipments()) {
                equipment.setSquadId(squad.getSquadId());
            }
            equipmentsClient.create(squad.getEquipments());
        }
        return find(squad.getSquadId());
    }

    @Override
    public Squad update(Squad squad) {
        repository.update(squad);
        if (CollectionUtils.isNotEmpty(squad.getMembers())) {
            for (Member member : squad.getMembers()) {
                member.setSquadId(squad.getSquadId());
            }
            membersClient.update(squad.getMembers());
        }
        if (CollectionUtils.isNotEmpty(squad.getEquipments())) {
            for (Equipment equipment : squad.getEquipments()) {
                equipment.setSquadId(squad.getSquadId());
            }
            equipmentsClient.update(squad.getEquipments());
        }
        return find(squad.getSquadId());
    }

    @Override
    public Collection<Squad> search(SquadsSearch search) {
        if (CollectionUtils.isEmpty(search.getSquadIds())) {
            return Collections.emptyList();
        }
        Collection<Squad> squads = repository.search(search);

        if (!squads.isEmpty() && search.isWithDetails()) {
            Set<String> squadIds = GroupUtils.extractUnique(squads, Squad::getSquadId);

            Map<String, List<Member>> membersMap = GroupUtils.groupMultipleBy(
                    membersClient.search(
                            MembersSearch.builder()
                                    .squadIds(squadIds)
                                    .build()
                    ),
                    Member::getSquadId
            );

            Map<String, List<Equipment>> equipmentsMap = GroupUtils.groupMultipleBy(
                    equipmentsClient.search(
                            EquipmentsSearch.builder()
                                    .squadIds(squadIds)
                                    .build()
                    ),
                    Equipment::getSquadId
            );

            for (Squad squad : squads) {
                squad.setMembers(membersMap.get(squad.getSquadId()));
                squad.setEquipments(equipmentsMap.get(squad.getSquadId()));
            }
        }

        return squads;
    }

    private Squad find(String squadId) {
        Collection<Squad> squads = search(
                SquadsSearch.builder()
                        .squadIds(Set.of(squadId))
                        .withDetails(true)
                        .build()
        );
        if (!squads.isEmpty()) {
            return squads.iterator().next();
        } else {
            log.error("Cannot find new squad {}", squadId);
            throw new IllegalArgumentException("Cannot find squad");
        }
    }
}
