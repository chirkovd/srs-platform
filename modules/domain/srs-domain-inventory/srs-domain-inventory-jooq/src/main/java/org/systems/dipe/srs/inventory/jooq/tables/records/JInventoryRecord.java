/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.inventory.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.systems.dipe.srs.inventory.jooq.tables.JInventory;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JInventoryRecord extends UpdatableRecordImpl<JInventoryRecord> implements Record3<UUID, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>inventories.inventory.inventory_id</code>.
     */
    public JInventoryRecord setInventoryId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>inventories.inventory.inventory_id</code>.
     */
    public UUID getInventoryId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>inventories.inventory.name</code>.
     */
    public JInventoryRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>inventories.inventory.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>inventories.inventory.created</code>.
     */
    public JInventoryRecord setCreated(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>inventories.inventory.created</code>.
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, String, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, String, LocalDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return JInventory.INVENTORY.INVENTORY_ID;
    }

    @Override
    public Field<String> field2() {
        return JInventory.INVENTORY.NAME;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return JInventory.INVENTORY.CREATED;
    }

    @Override
    public UUID component1() {
        return getInventoryId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public LocalDateTime component3() {
        return getCreated();
    }

    @Override
    public UUID value1() {
        return getInventoryId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public LocalDateTime value3() {
        return getCreated();
    }

    @Override
    public JInventoryRecord value1(UUID value) {
        setInventoryId(value);
        return this;
    }

    @Override
    public JInventoryRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public JInventoryRecord value3(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    @Override
    public JInventoryRecord values(UUID value1, String value2, LocalDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JInventoryRecord
     */
    public JInventoryRecord() {
        super(JInventory.INVENTORY);
    }

    /**
     * Create a detached, initialised JInventoryRecord
     */
    public JInventoryRecord(UUID inventoryId, String name, LocalDateTime created) {
        super(JInventory.INVENTORY);

        setInventoryId(inventoryId);
        setName(name);
        setCreated(created);
    }
}
