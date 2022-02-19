/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.person.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;
import org.systems.dipe.srs.person.jooq.tables.JRoleLink;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRoleLinkRecord extends TableRecordImpl<JRoleLinkRecord> implements Record3<UUID, UUID, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>srs_people.role_link.role_id</code>.
     */
    public JRoleLinkRecord setRoleId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>srs_people.role_link.role_id</code>.
     */
    public UUID getRoleId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>srs_people.role_link.person_id</code>.
     */
    public JRoleLinkRecord setPersonId(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>srs_people.role_link.person_id</code>.
     */
    public UUID getPersonId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>srs_people.role_link.created</code>.
     */
    public JRoleLinkRecord setCreated(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>srs_people.role_link.created</code>.
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, UUID, LocalDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return JRoleLink.ROLE_LINK.ROLE_ID;
    }

    @Override
    public Field<UUID> field2() {
        return JRoleLink.ROLE_LINK.PERSON_ID;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return JRoleLink.ROLE_LINK.CREATED;
    }

    @Override
    public UUID component1() {
        return getRoleId();
    }

    @Override
    public UUID component2() {
        return getPersonId();
    }

    @Override
    public LocalDateTime component3() {
        return getCreated();
    }

    @Override
    public UUID value1() {
        return getRoleId();
    }

    @Override
    public UUID value2() {
        return getPersonId();
    }

    @Override
    public LocalDateTime value3() {
        return getCreated();
    }

    @Override
    public JRoleLinkRecord value1(UUID value) {
        setRoleId(value);
        return this;
    }

    @Override
    public JRoleLinkRecord value2(UUID value) {
        setPersonId(value);
        return this;
    }

    @Override
    public JRoleLinkRecord value3(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    @Override
    public JRoleLinkRecord values(UUID value1, UUID value2, LocalDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JRoleLinkRecord
     */
    public JRoleLinkRecord() {
        super(JRoleLink.ROLE_LINK);
    }

    /**
     * Create a detached, initialised JRoleLinkRecord
     */
    public JRoleLinkRecord(UUID roleId, UUID personId, LocalDateTime created) {
        super(JRoleLink.ROLE_LINK);

        setRoleId(roleId);
        setPersonId(personId);
        setCreated(created);
    }
}
