package org.systems.dipe.srs.squad;

import java.util.Collection;

public interface SquadClient {

    Squad create(Squad squad);

    Squad update(Squad squad);

    void addMembers(Collection<Member> members);

    void addEquipments(Collection<Equipment> equipments);

}
