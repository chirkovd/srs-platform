/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.request.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.systems.dipe.srs.request.jooq.tables.JRequestLocation;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRequestLocationRecord extends UpdatableRecordImpl<JRequestLocationRecord> implements Record3<UUID, UUID, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>requests.request_location.location_id</code>.
     */
    public JRequestLocationRecord setLocationId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>requests.request_location.location_id</code>.
     */
    public UUID getLocationId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>requests.request_location.request_id</code>.
     */
    public JRequestLocationRecord setRequestId(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>requests.request_location.request_id</code>.
     */
    public UUID getRequestId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>requests.request_location.created</code>.
     */
    public JRequestLocationRecord setCreated(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>requests.request_location.created</code>.
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
    public Row3<UUID, UUID, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, UUID, LocalDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return JRequestLocation.REQUEST_LOCATION.LOCATION_ID;
    }

    @Override
    public Field<UUID> field2() {
        return JRequestLocation.REQUEST_LOCATION.REQUEST_ID;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return JRequestLocation.REQUEST_LOCATION.CREATED;
    }

    @Override
    public UUID component1() {
        return getLocationId();
    }

    @Override
    public UUID component2() {
        return getRequestId();
    }

    @Override
    public LocalDateTime component3() {
        return getCreated();
    }

    @Override
    public UUID value1() {
        return getLocationId();
    }

    @Override
    public UUID value2() {
        return getRequestId();
    }

    @Override
    public LocalDateTime value3() {
        return getCreated();
    }

    @Override
    public JRequestLocationRecord value1(UUID value) {
        setLocationId(value);
        return this;
    }

    @Override
    public JRequestLocationRecord value2(UUID value) {
        setRequestId(value);
        return this;
    }

    @Override
    public JRequestLocationRecord value3(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    @Override
    public JRequestLocationRecord values(UUID value1, UUID value2, LocalDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JRequestLocationRecord
     */
    public JRequestLocationRecord() {
        super(JRequestLocation.REQUEST_LOCATION);
    }

    /**
     * Create a detached, initialised JRequestLocationRecord
     */
    public JRequestLocationRecord(UUID locationId, UUID requestId, LocalDateTime created) {
        super(JRequestLocation.REQUEST_LOCATION);

        setLocationId(locationId);
        setRequestId(requestId);
        setCreated(created);
    }
}
