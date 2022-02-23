/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.request.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;
import org.systems.dipe.srs.request.jooq.tables.JRequestItem;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRequestItemRecord extends UpdatableRecordImpl<JRequestItemRecord> implements Record6<UUID, UUID, UUID, LocalDateTime, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>requests.request_item.item_id</code>.
     */
    public JRequestItemRecord setItemId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>requests.request_item.item_id</code>.
     */
    public UUID getItemId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>requests.request_item.request_id</code>.
     */
    public JRequestItemRecord setRequestId(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>requests.request_item.request_id</code>.
     */
    public UUID getRequestId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>requests.request_item.target_id</code>.
     */
    public JRequestItemRecord setTargetId(UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>requests.request_item.target_id</code>.
     */
    public UUID getTargetId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>requests.request_item.approved</code>.
     */
    public JRequestItemRecord setApproved(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>requests.request_item.approved</code>.
     */
    public LocalDateTime getApproved() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>requests.request_item.dismissed</code>.
     */
    public JRequestItemRecord setDismissed(LocalDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>requests.request_item.dismissed</code>.
     */
    public LocalDateTime getDismissed() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>requests.request_item.created</code>.
     */
    public JRequestItemRecord setCreated(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>requests.request_item.created</code>.
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<UUID, UUID, UUID, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<UUID, UUID, UUID, LocalDateTime, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return JRequestItem.REQUEST_ITEM.ITEM_ID;
    }

    @Override
    public Field<UUID> field2() {
        return JRequestItem.REQUEST_ITEM.REQUEST_ID;
    }

    @Override
    public Field<UUID> field3() {
        return JRequestItem.REQUEST_ITEM.TARGET_ID;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return JRequestItem.REQUEST_ITEM.APPROVED;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return JRequestItem.REQUEST_ITEM.DISMISSED;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return JRequestItem.REQUEST_ITEM.CREATED;
    }

    @Override
    public UUID component1() {
        return getItemId();
    }

    @Override
    public UUID component2() {
        return getRequestId();
    }

    @Override
    public UUID component3() {
        return getTargetId();
    }

    @Override
    public LocalDateTime component4() {
        return getApproved();
    }

    @Override
    public LocalDateTime component5() {
        return getDismissed();
    }

    @Override
    public LocalDateTime component6() {
        return getCreated();
    }

    @Override
    public UUID value1() {
        return getItemId();
    }

    @Override
    public UUID value2() {
        return getRequestId();
    }

    @Override
    public UUID value3() {
        return getTargetId();
    }

    @Override
    public LocalDateTime value4() {
        return getApproved();
    }

    @Override
    public LocalDateTime value5() {
        return getDismissed();
    }

    @Override
    public LocalDateTime value6() {
        return getCreated();
    }

    @Override
    public JRequestItemRecord value1(UUID value) {
        setItemId(value);
        return this;
    }

    @Override
    public JRequestItemRecord value2(UUID value) {
        setRequestId(value);
        return this;
    }

    @Override
    public JRequestItemRecord value3(UUID value) {
        setTargetId(value);
        return this;
    }

    @Override
    public JRequestItemRecord value4(LocalDateTime value) {
        setApproved(value);
        return this;
    }

    @Override
    public JRequestItemRecord value5(LocalDateTime value) {
        setDismissed(value);
        return this;
    }

    @Override
    public JRequestItemRecord value6(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    @Override
    public JRequestItemRecord values(UUID value1, UUID value2, UUID value3, LocalDateTime value4, LocalDateTime value5, LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JRequestItemRecord
     */
    public JRequestItemRecord() {
        super(JRequestItem.REQUEST_ITEM);
    }

    /**
     * Create a detached, initialised JRequestItemRecord
     */
    public JRequestItemRecord(UUID itemId, UUID requestId, UUID targetId, LocalDateTime approved, LocalDateTime dismissed, LocalDateTime created) {
        super(JRequestItem.REQUEST_ITEM);

        setItemId(itemId);
        setRequestId(requestId);
        setTargetId(targetId);
        setApproved(approved);
        setDismissed(dismissed);
        setCreated(created);
    }
}
