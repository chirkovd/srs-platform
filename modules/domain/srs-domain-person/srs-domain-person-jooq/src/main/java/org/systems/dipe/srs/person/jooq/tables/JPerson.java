/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.person.jooq.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.systems.dipe.srs.person.jooq.JPeople;
import org.systems.dipe.srs.person.jooq.Keys;
import org.systems.dipe.srs.person.jooq.tables.records.JPersonRecord;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JPerson extends TableImpl<JPersonRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>people.person</code>
     */
    public static final JPerson PERSON = new JPerson();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JPersonRecord> getRecordType() {
        return JPersonRecord.class;
    }

    /**
     * The column <code>people.person.person_id</code>.
     */
    public final TableField<JPersonRecord, UUID> PERSON_ID = createField(DSL.name("person_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>people.person.first_name</code>.
     */
    public final TableField<JPersonRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>people.person.last_name</code>.
     */
    public final TableField<JPersonRecord, String> LAST_NAME = createField(DSL.name("last_name"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>people.person.created</code>.
     */
    public final TableField<JPersonRecord, LocalDateTime> CREATED = createField(DSL.name("created"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    private JPerson(Name alias, Table<JPersonRecord> aliased) {
        this(alias, aliased, null);
    }

    private JPerson(Name alias, Table<JPersonRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>people.person</code> table reference
     */
    public JPerson(String alias) {
        this(DSL.name(alias), PERSON);
    }

    /**
     * Create an aliased <code>people.person</code> table reference
     */
    public JPerson(Name alias) {
        this(alias, PERSON);
    }

    /**
     * Create a <code>people.person</code> table reference
     */
    public JPerson() {
        this(DSL.name("person"), null);
    }

    public <O extends Record> JPerson(Table<O> child, ForeignKey<O, JPersonRecord> key) {
        super(child, key, PERSON);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : JPeople.PEOPLE;
    }

    @Override
    public UniqueKey<JPersonRecord> getPrimaryKey() {
        return Keys.PK_PEOPLE_PERSON;
    }

    @Override
    public JPerson as(String alias) {
        return new JPerson(DSL.name(alias), this);
    }

    @Override
    public JPerson as(Name alias) {
        return new JPerson(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JPerson rename(String name) {
        return new JPerson(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JPerson rename(Name name) {
        return new JPerson(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, String, String, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
