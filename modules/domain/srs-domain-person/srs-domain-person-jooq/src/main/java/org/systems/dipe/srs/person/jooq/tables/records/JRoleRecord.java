/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.person.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.systems.dipe.srs.person.jooq.tables.JRole;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRoleRecord extends UpdatableRecordImpl<JRoleRecord> implements Record4<UUID, String, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>people.role.role_id</code>.
     */
    public JRoleRecord setRoleId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>people.role.role_id</code>.
     */
    public UUID getRoleId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>people.role.role</code>.
     */
    public JRoleRecord setRole(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>people.role.role</code>.
     */
    public String getRole() {
        return (String) get(1);
    }

    /**
     * Setter for <code>people.role.description</code>.
     */
    public JRoleRecord setDescription(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>people.role.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>people.role.created</code>.
     */
    public JRoleRecord setCreated(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>people.role.created</code>.
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, String, String, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<UUID, String, String, LocalDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return JRole.ROLE.ROLE_ID;
    }

    @Override
    public Field<String> field2() {
        return JRole.ROLE.ROLE_;
    }

    @Override
    public Field<String> field3() {
        return JRole.ROLE.DESCRIPTION;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return JRole.ROLE.CREATED;
    }

    @Override
    public UUID component1() {
        return getRoleId();
    }

    @Override
    public String component2() {
        return getRole();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public LocalDateTime component4() {
        return getCreated();
    }

    @Override
    public UUID value1() {
        return getRoleId();
    }

    @Override
    public String value2() {
        return getRole();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public LocalDateTime value4() {
        return getCreated();
    }

    @Override
    public JRoleRecord value1(UUID value) {
        setRoleId(value);
        return this;
    }

    @Override
    public JRoleRecord value2(String value) {
        setRole(value);
        return this;
    }

    @Override
    public JRoleRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public JRoleRecord value4(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    @Override
    public JRoleRecord values(UUID value1, String value2, String value3, LocalDateTime value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JRoleRecord
     */
    public JRoleRecord() {
        super(JRole.ROLE);
    }

    /**
     * Create a detached, initialised JRoleRecord
     */
    public JRoleRecord(UUID roleId, String role, String description, LocalDateTime created) {
        super(JRole.ROLE);

        setRoleId(roleId);
        setRole(role);
        setDescription(description);
        setCreated(created);
    }
}