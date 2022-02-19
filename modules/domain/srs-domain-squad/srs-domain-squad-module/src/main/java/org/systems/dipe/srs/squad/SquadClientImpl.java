package org.systems.dipe.srs.squad;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.squad.storage.SquadRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SquadClientImpl implements SquadClient {

    private final SquadRepository squadRepository;

    @Override
    public Squad create(Squad squad) {
        return squadRepository.create(squad);
    }

    @Override
    public Squad update(Squad squad) {
        return squadRepository.update(squad);
    }

    @Override
    public void addMembers(Collection<Member> members) {
        squadRepository.addMembers(members);
    }

    @Override
    public void addEquipments(Collection<Equipment> equipments) {
        squadRepository.addEquipments(equipments);
    }
}
