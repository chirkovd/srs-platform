/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.person.jooq.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.systems.dipe.srs.person.jooq.JSquads;
import org.systems.dipe.srs.person.jooq.Keys;
import org.systems.dipe.srs.person.jooq.tables.records.JMemberRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JMember extends TableImpl<JMemberRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>squads.member</code>
     */
    public static final JMember MEMBER = new JMember();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JMemberRecord> getRecordType() {
        return JMemberRecord.class;
    }

    /**
     * The column <code>squads.member.member_id</code>.
     */
    public final TableField<JMemberRecord, UUID> MEMBER_ID = createField(DSL.name("member_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>squads.member.squad_id</code>.
     */
    public final TableField<JMemberRecord, UUID> SQUAD_ID = createField(DSL.name("squad_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>squads.member.volunteer_id</code>.
     */
    public final TableField<JMemberRecord, UUID> VOLUNTEER_ID = createField(DSL.name("volunteer_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>squads.member.head</code>.
     */
    public final TableField<JMemberRecord, Boolean> HEAD = createField(DSL.name("head"), SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>squads.member.created</code>.
     */
    public final TableField<JMemberRecord, LocalDateTime> CREATED = createField(DSL.name("created"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    private JMember(Name alias, Table<JMemberRecord> aliased) {
        this(alias, aliased, null);
    }

    private JMember(Name alias, Table<JMemberRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>squads.member</code> table reference
     */
    public JMember(String alias) {
        this(DSL.name(alias), MEMBER);
    }

    /**
     * Create an aliased <code>squads.member</code> table reference
     */
    public JMember(Name alias) {
        this(alias, MEMBER);
    }

    /**
     * Create a <code>squads.member</code> table reference
     */
    public JMember() {
        this(DSL.name("member"), null);
    }

    public <O extends Record> JMember(Table<O> child, ForeignKey<O, JMemberRecord> key) {
        super(child, key, MEMBER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : JSquads.SQUADS;
    }

    @Override
    public UniqueKey<JMemberRecord> getPrimaryKey() {
        return Keys.PK_SQUADS_MEMBER;
    }

    @Override
    public List<ForeignKey<JMemberRecord, ?>> getReferences() {
        return Arrays.asList(Keys.MEMBER__FK_SQUADS_MEMBER_SQUAD);
    }

    private transient JSquad _squad;

    /**
     * Get the implicit join path to the <code>squads.squad</code> table.
     */
    public JSquad squad() {
        if (_squad == null)
            _squad = new JSquad(this, Keys.MEMBER__FK_SQUADS_MEMBER_SQUAD);

        return _squad;
    }

    @Override
    public JMember as(String alias) {
        return new JMember(DSL.name(alias), this);
    }

    @Override
    public JMember as(Name alias) {
        return new JMember(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JMember rename(String name) {
        return new JMember(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JMember rename(Name name) {
        return new JMember(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<UUID, UUID, UUID, Boolean, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
