/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.request.jooq.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.systems.dipe.srs.request.jooq.JRequests;
import org.systems.dipe.srs.request.jooq.Keys;
import org.systems.dipe.srs.request.jooq.tables.records.JRequestItemRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRequestItem extends TableImpl<JRequestItemRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>requests.request_item</code>
     */
    public static final JRequestItem REQUEST_ITEM = new JRequestItem();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JRequestItemRecord> getRecordType() {
        return JRequestItemRecord.class;
    }

    /**
     * The column <code>requests.request_item.item_id</code>.
     */
    public final TableField<JRequestItemRecord, UUID> ITEM_ID = createField(DSL.name("item_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>requests.request_item.request_id</code>.
     */
    public final TableField<JRequestItemRecord, UUID> REQUEST_ID = createField(DSL.name("request_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>requests.request_item.target_id</code>.
     */
    public final TableField<JRequestItemRecord, UUID> TARGET_ID = createField(DSL.name("target_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>requests.request_item.approved</code>.
     */
    public final TableField<JRequestItemRecord, LocalDateTime> APPROVED = createField(DSL.name("approved"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>requests.request_item.dismissed</code>.
     */
    public final TableField<JRequestItemRecord, LocalDateTime> DISMISSED = createField(DSL.name("dismissed"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>requests.request_item.created</code>.
     */
    public final TableField<JRequestItemRecord, LocalDateTime> CREATED = createField(DSL.name("created"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private JRequestItem(Name alias, Table<JRequestItemRecord> aliased) {
        this(alias, aliased, null);
    }

    private JRequestItem(Name alias, Table<JRequestItemRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>requests.request_item</code> table reference
     */
    public JRequestItem(String alias) {
        this(DSL.name(alias), REQUEST_ITEM);
    }

    /**
     * Create an aliased <code>requests.request_item</code> table reference
     */
    public JRequestItem(Name alias) {
        this(alias, REQUEST_ITEM);
    }

    /**
     * Create a <code>requests.request_item</code> table reference
     */
    public JRequestItem() {
        this(DSL.name("request_item"), null);
    }

    public <O extends Record> JRequestItem(Table<O> child, ForeignKey<O, JRequestItemRecord> key) {
        super(child, key, REQUEST_ITEM);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : JRequests.REQUESTS;
    }

    @Override
    public UniqueKey<JRequestItemRecord> getPrimaryKey() {
        return Keys.PK_REQUESTS_REQUEST_ITEM;
    }

    @Override
    public List<ForeignKey<JRequestItemRecord, ?>> getReferences() {
        return Arrays.asList(Keys.REQUEST_ITEM__FK_REQUESTS_REQUEST_ITEM_REQUEST_ID);
    }

    private transient JRequest _request;

    /**
     * Get the implicit join path to the <code>requests.request</code> table.
     */
    public JRequest request() {
        if (_request == null)
            _request = new JRequest(this, Keys.REQUEST_ITEM__FK_REQUESTS_REQUEST_ITEM_REQUEST_ID);

        return _request;
    }

    @Override
    public JRequestItem as(String alias) {
        return new JRequestItem(DSL.name(alias), this);
    }

    @Override
    public JRequestItem as(Name alias) {
        return new JRequestItem(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JRequestItem rename(String name) {
        return new JRequestItem(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JRequestItem rename(Name name) {
        return new JRequestItem(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<UUID, UUID, UUID, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
