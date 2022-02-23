package org.systems.dipe.srs.squad.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.squad.Equipment;
import org.systems.dipe.srs.squad.EquipmentsSearch;
import org.systems.dipe.srs.squad.jooq.tables.JEquipment;
import org.systems.dipe.srs.squad.storage.EquipmentsRepository;
import org.systems.dipe.srs.squad.storage.mapper.EquipmentsMapper;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Repository
@Transactional
@AllArgsConstructor
public class EquipmentsJooqRepository implements EquipmentsRepository {

    private final DefaultDSLContext dsl;
    private final EquipmentsMapper mapper;

    @Override
    public void create(Collection<Equipment> equipments) {
        dsl.batchInsert(mapper.toJooq(equipments)).execute();
    }

    @Override
    public void update(Collection<Equipment> equipments) {
        // clean up previous equipments
        Set<String> squadIds = GroupUtils.extractUnique(equipments, Equipment::getSquadId);
        dsl.deleteFrom(JEquipment.EQUIPMENT)
                .where(JEquipment.EQUIPMENT.SQUAD_ID.in(UuidUtils.fromStr(squadIds)))
                .execute();

        create(equipments);
    }

    @Override
    public Collection<Equipment> search(EquipmentsSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getSquadIds())) {
            conditions.add(JEquipment.EQUIPMENT.SQUAD_ID.in(UuidUtils.fromStr(search.getSquadIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getInventoryIds())) {
            conditions.add(JEquipment.EQUIPMENT.INVENTORY_ID.in(UuidUtils.fromStr(search.getInventoryIds())));
        }
        return dsl.selectFrom(JEquipment.EQUIPMENT)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
