package org.systems.dipe.srs.squad.storage;

import org.systems.dipe.srs.squad.Equipment;
import org.systems.dipe.srs.squad.Member;
import org.systems.dipe.srs.squad.Squad;

import java.util.Collection;

public interface SquadRepository {

    Squad create(Squad squad);

    Squad update(Squad squad);

    void addMembers(Collection<Member> members);

    void addEquipments(Collection<Equipment> equipments);

}
