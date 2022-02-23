package org.systems.dipe.srs.inventory.storage.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.inventory.Inventory;
import org.systems.dipe.srs.inventory.jooq.tables.records.JInventoryRecord;
import org.systems.dipe.srs.mappers.CommonMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface InventoryMapper extends CommonMapper {

    JInventoryRecord toJooq(Inventory inventory);

    Inventory fromJooq(JInventoryRecord record);

}
