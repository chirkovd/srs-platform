package org.systems.dipe.srs.inventory;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EquipmentClientImpl implements EquipmentClient {

    @Override
    public Equipment create(Equipment equipment) {
        return null;
    }

    @Override
    public List<Equipment> search(EquipmentSearch search) {
        return Collections.emptyList();
    }
}
