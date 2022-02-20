/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.squad.jooq.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.systems.dipe.srs.squad.jooq.Indexes;
import org.systems.dipe.srs.squad.jooq.JSquads;
import org.systems.dipe.srs.squad.jooq.Keys;
import org.systems.dipe.srs.squad.jooq.tables.records.JEquipmentRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JEquipment extends TableImpl<JEquipmentRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>squads.equipment</code>
     */
    public static final JEquipment EQUIPMENT = new JEquipment();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JEquipmentRecord> getRecordType() {
        return JEquipmentRecord.class;
    }

    /**
     * The column <code>squads.equipment.inventory_id</code>.
     */
    public final TableField<JEquipmentRecord, UUID> INVENTORY_ID = createField(DSL.name("inventory_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>squads.equipment.squad_id</code>.
     */
    public final TableField<JEquipmentRecord, UUID> SQUAD_ID = createField(DSL.name("squad_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>squads.equipment.created</code>.
     */
    public final TableField<JEquipmentRecord, LocalDateTime> CREATED = createField(DSL.name("created"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private JEquipment(Name alias, Table<JEquipmentRecord> aliased) {
        this(alias, aliased, null);
    }

    private JEquipment(Name alias, Table<JEquipmentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>squads.equipment</code> table reference
     */
    public JEquipment(String alias) {
        this(DSL.name(alias), EQUIPMENT);
    }

    /**
     * Create an aliased <code>squads.equipment</code> table reference
     */
    public JEquipment(Name alias) {
        this(alias, EQUIPMENT);
    }

    /**
     * Create a <code>squads.equipment</code> table reference
     */
    public JEquipment() {
        this(DSL.name("equipment"), null);
    }

    public <O extends Record> JEquipment(Table<O> child, ForeignKey<O, JEquipmentRecord> key) {
        super(child, key, EQUIPMENT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : JSquads.SQUADS;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IX_SQUADS_EQUIPMENT_INVENTORY_SQUAD);
    }

    @Override
    public List<ForeignKey<JEquipmentRecord, ?>> getReferences() {
        return Arrays.asList(Keys.EQUIPMENT__FK_SQUADS_EQUIPMENT_SQUAD);
    }

    private transient JSquad _squad;

    /**
     * Get the implicit join path to the <code>squads.squad</code> table.
     */
    public JSquad squad() {
        if (_squad == null)
            _squad = new JSquad(this, Keys.EQUIPMENT__FK_SQUADS_EQUIPMENT_SQUAD);

        return _squad;
    }

    @Override
    public JEquipment as(String alias) {
        return new JEquipment(DSL.name(alias), this);
    }

    @Override
    public JEquipment as(Name alias) {
        return new JEquipment(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JEquipment rename(String name) {
        return new JEquipment(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JEquipment rename(Name name) {
        return new JEquipment(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
