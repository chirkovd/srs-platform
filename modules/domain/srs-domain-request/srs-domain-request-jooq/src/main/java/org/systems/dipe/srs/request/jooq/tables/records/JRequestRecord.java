/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.request.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.systems.dipe.srs.request.jooq.tables.JRequest;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRequestRecord extends UpdatableRecordImpl<JRequestRecord> implements Record5<UUID, UUID, UUID, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>requests.request.request_id</code>.
     */
    public JRequestRecord setRequestId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>requests.request.request_id</code>.
     */
    public UUID getRequestId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>requests.request.customer_id</code>.
     */
    public JRequestRecord setCustomerId(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>requests.request.customer_id</code>.
     */
    public UUID getCustomerId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>requests.request.supervisor_id</code>.
     */
    public JRequestRecord setSupervisorId(UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>requests.request.supervisor_id</code>.
     */
    public UUID getSupervisorId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>requests.request.approved</code>.
     */
    public JRequestRecord setApproved(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>requests.request.approved</code>.
     */
    public LocalDateTime getApproved() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>requests.request.created</code>.
     */
    public JRequestRecord setCreated(LocalDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>requests.request.created</code>.
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<UUID, UUID, UUID, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<UUID, UUID, UUID, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return JRequest.REQUEST.REQUEST_ID;
    }

    @Override
    public Field<UUID> field2() {
        return JRequest.REQUEST.CUSTOMER_ID;
    }

    @Override
    public Field<UUID> field3() {
        return JRequest.REQUEST.SUPERVISOR_ID;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return JRequest.REQUEST.APPROVED;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return JRequest.REQUEST.CREATED;
    }

    @Override
    public UUID component1() {
        return getRequestId();
    }

    @Override
    public UUID component2() {
        return getCustomerId();
    }

    @Override
    public UUID component3() {
        return getSupervisorId();
    }

    @Override
    public LocalDateTime component4() {
        return getApproved();
    }

    @Override
    public LocalDateTime component5() {
        return getCreated();
    }

    @Override
    public UUID value1() {
        return getRequestId();
    }

    @Override
    public UUID value2() {
        return getCustomerId();
    }

    @Override
    public UUID value3() {
        return getSupervisorId();
    }

    @Override
    public LocalDateTime value4() {
        return getApproved();
    }

    @Override
    public LocalDateTime value5() {
        return getCreated();
    }

    @Override
    public JRequestRecord value1(UUID value) {
        setRequestId(value);
        return this;
    }

    @Override
    public JRequestRecord value2(UUID value) {
        setCustomerId(value);
        return this;
    }

    @Override
    public JRequestRecord value3(UUID value) {
        setSupervisorId(value);
        return this;
    }

    @Override
    public JRequestRecord value4(LocalDateTime value) {
        setApproved(value);
        return this;
    }

    @Override
    public JRequestRecord value5(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    @Override
    public JRequestRecord values(UUID value1, UUID value2, UUID value3, LocalDateTime value4, LocalDateTime value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JRequestRecord
     */
    public JRequestRecord() {
        super(JRequest.REQUEST);
    }

    /**
     * Create a detached, initialised JRequestRecord
     */
    public JRequestRecord(UUID requestId, UUID customerId, UUID supervisorId, LocalDateTime approved, LocalDateTime created) {
        super(JRequest.REQUEST);

        setRequestId(requestId);
        setCustomerId(customerId);
        setSupervisorId(supervisorId);
        setApproved(approved);
        setCreated(created);
    }
}
