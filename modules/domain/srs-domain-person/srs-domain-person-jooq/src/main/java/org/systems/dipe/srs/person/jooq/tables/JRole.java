/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.person.jooq.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.systems.dipe.srs.person.jooq.Indexes;
import org.systems.dipe.srs.person.jooq.JPeople;
import org.systems.dipe.srs.person.jooq.Keys;
import org.systems.dipe.srs.person.jooq.tables.records.JRoleRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRole extends TableImpl<JRoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>people.role</code>
     */
    public static final JRole ROLE = new JRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JRoleRecord> getRecordType() {
        return JRoleRecord.class;
    }

    /**
     * The column <code>people.role.role_id</code>.
     */
    public final TableField<JRoleRecord, UUID> ROLE_ID = createField(DSL.name("role_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>people.role.role</code>.
     */
    public final TableField<JRoleRecord, String> ROLE_ = createField(DSL.name("role"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>people.role.description</code>.
     */
    public final TableField<JRoleRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>people.role.created</code>.
     */
    public final TableField<JRoleRecord, LocalDateTime> CREATED = createField(DSL.name("created"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private JRole(Name alias, Table<JRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private JRole(Name alias, Table<JRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>people.role</code> table reference
     */
    public JRole(String alias) {
        this(DSL.name(alias), ROLE);
    }

    /**
     * Create an aliased <code>people.role</code> table reference
     */
    public JRole(Name alias) {
        this(alias, ROLE);
    }

    /**
     * Create a <code>people.role</code> table reference
     */
    public JRole() {
        this(DSL.name("role"), null);
    }

    public <O extends Record> JRole(Table<O> child, ForeignKey<O, JRoleRecord> key) {
        super(child, key, ROLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : JPeople.PEOPLE;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IX_PEOPLE_ROLE_ROLE);
    }

    @Override
    public UniqueKey<JRoleRecord> getPrimaryKey() {
        return Keys.PK_PEOPLE_ROLE;
    }

    @Override
    public JRole as(String alias) {
        return new JRole(DSL.name(alias), this);
    }

    @Override
    public JRole as(Name alias) {
        return new JRole(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JRole rename(String name) {
        return new JRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JRole rename(Name name) {
        return new JRole(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, String, String, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
