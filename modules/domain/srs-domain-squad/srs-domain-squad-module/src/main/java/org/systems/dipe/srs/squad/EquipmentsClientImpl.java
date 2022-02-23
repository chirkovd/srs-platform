package org.systems.dipe.srs.squad;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.squad.storage.EquipmentsRepository;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class EquipmentsClientImpl implements EquipmentsClient {

    private final EquipmentsRepository repository;

    @Override
    public void create(Collection<Equipment> equipments) {
        if (CollectionUtils.isEmpty(equipments)) {
            return;
        }
        prepareEquipments(equipments);
        repository.create(equipments);
    }

    @Override
    public void update(Collection<Equipment> equipments) {
        if (CollectionUtils.isEmpty(equipments)) {
            return;
        }
        prepareEquipments(equipments);
        repository.update(equipments);
    }

    @Override
    public Collection<Equipment> search(EquipmentsSearch search) {
        if (CollectionUtils.isEmpty(search.getSquadIds())
                && CollectionUtils.isEmpty(search.getInventoryIds())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }

    private static void prepareEquipments(Collection<Equipment> equipments) {
        for (Equipment equipment : equipments) {
            if (Objects.isNull(equipment.getInventoryId())) {
                equipment.setInventoryId(UuidUtils.newStr());
            }
            if (Objects.isNull(equipment.getCreated())) {
                equipment.setCreated(TimeUtils.now());
            }
        }
    }
}
