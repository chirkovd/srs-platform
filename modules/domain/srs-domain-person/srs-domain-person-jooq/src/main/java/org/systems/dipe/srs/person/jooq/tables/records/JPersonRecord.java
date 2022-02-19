/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.person.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.systems.dipe.srs.person.jooq.tables.JPerson;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JPersonRecord extends UpdatableRecordImpl<JPersonRecord> implements Record3<UUID, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>people.person.person_id</code>.
     */
    public JPersonRecord setPersonId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>people.person.person_id</code>.
     */
    public UUID getPersonId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>people.person.username</code>.
     */
    public JPersonRecord setUsername(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>people.person.username</code>.
     */
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>people.person.created</code>.
     */
    public JPersonRecord setCreated(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>people.person.created</code>.
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
        return JPerson.PERSON.PERSON_ID;
    }

    @Override
    public Field<String> field2() {
        return JPerson.PERSON.USERNAME;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return JPerson.PERSON.CREATED;
    }

    @Override
    public UUID component1() {
        return getPersonId();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public LocalDateTime component3() {
        return getCreated();
    }

    @Override
    public UUID value1() {
        return getPersonId();
    }

    @Override
    public String value2() {
        return getUsername();
    }

    @Override
    public LocalDateTime value3() {
        return getCreated();
    }

    @Override
    public JPersonRecord value1(UUID value) {
        setPersonId(value);
        return this;
    }

    @Override
    public JPersonRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public JPersonRecord value3(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    @Override
    public JPersonRecord values(UUID value1, String value2, LocalDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JPersonRecord
     */
    public JPersonRecord() {
        super(JPerson.PERSON);
    }

    /**
     * Create a detached, initialised JPersonRecord
     */
    public JPersonRecord(UUID personId, String username, LocalDateTime created) {
        super(JPerson.PERSON);

        setPersonId(personId);
        setUsername(username);
        setCreated(created);
    }
}
