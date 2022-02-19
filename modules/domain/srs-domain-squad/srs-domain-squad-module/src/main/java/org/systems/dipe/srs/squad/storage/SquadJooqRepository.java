package org.systems.dipe.srs.squad.storage;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.squad.Equipment;
import org.systems.dipe.srs.squad.Member;
import org.systems.dipe.srs.squad.Squad;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class SquadJooqRepository implements SquadRepository{

    private final DefaultDSLContext dsl;

    @Override
    public Squad create(Squad squad) {
        return null;
    }

    @Override
    public Squad update(Squad squad) {
        return null;
    }

    @Override
    public void addMembers(Collection<Member> members) {

    }

    @Override
    public void addEquipments(Collection<Equipment> equipments) {

    }
}
