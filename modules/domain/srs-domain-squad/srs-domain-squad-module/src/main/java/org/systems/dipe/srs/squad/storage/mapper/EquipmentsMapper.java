package org.systems.dipe.srs.squad.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.squad.Equipment;
import org.systems.dipe.srs.squad.jooq.tables.records.JEquipmentRecord;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EquipmentsMapper extends CommonMapper {

    JEquipmentRecord toJooq(Equipment equipment);

    Collection<JEquipmentRecord> toJooq(Collection<Equipment> equipments);

    Equipment fromJooq(JEquipmentRecord record);
}
