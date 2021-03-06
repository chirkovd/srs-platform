/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.request.jooq.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.systems.dipe.srs.request.jooq.Indexes;
import org.systems.dipe.srs.request.jooq.JRequests;
import org.systems.dipe.srs.request.jooq.Keys;
import org.systems.dipe.srs.request.jooq.tables.records.JRequestLocationRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JRequestLocation extends TableImpl<JRequestLocationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>requests.request_location</code>
     */
    public static final JRequestLocation REQUEST_LOCATION = new JRequestLocation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JRequestLocationRecord> getRecordType() {
        return JRequestLocationRecord.class;
    }

    /**
     * The column <code>requests.request_location.location_id</code>.
     */
    public final TableField<JRequestLocationRecord, UUID> LOCATION_ID = createField(DSL.name("location_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>requests.request_location.request_id</code>.
     */
    public final TableField<JRequestLocationRecord, UUID> REQUEST_ID = createField(DSL.name("request_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>requests.request_location.created</code>.
     */
    public final TableField<JRequestLocationRecord, LocalDateTime> CREATED = createField(DSL.name("created"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private JRequestLocation(Name alias, Table<JRequestLocationRecord> aliased) {
        this(alias, aliased, null);
    }

    private JRequestLocation(Name alias, Table<JRequestLocationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>requests.request_location</code> table reference
     */
    public JRequestLocation(String alias) {
        this(DSL.name(alias), REQUEST_LOCATION);
    }

    /**
     * Create an aliased <code>requests.request_location</code> table reference
     */
    public JRequestLocation(Name alias) {
        this(alias, REQUEST_LOCATION);
    }

    /**
     * Create a <code>requests.request_location</code> table reference
     */
    public JRequestLocation() {
        this(DSL.name("request_location"), null);
    }

    public <O extends Record> JRequestLocation(Table<O> child, ForeignKey<O, JRequestLocationRecord> key) {
        super(child, key, REQUEST_LOCATION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : JRequests.REQUESTS;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IX_REQUESTS_REQUEST_LOCATION_LOCATION_REQUEST);
    }

    @Override
    public UniqueKey<JRequestLocationRecord> getPrimaryKey() {
        return Keys.PK_REQUESTS_REQUEST_LOCATION;
    }

    @Override
    public List<ForeignKey<JRequestLocationRecord, ?>> getReferences() {
        return Arrays.asList(Keys.REQUEST_LOCATION__FK_REQUESTS_REQUEST_LOCATION_REQUEST_ID);
    }

    private transient JRequest _request;

    /**
     * Get the implicit join path to the <code>requests.request</code> table.
     */
    public JRequest request() {
        if (_request == null)
            _request = new JRequest(this, Keys.REQUEST_LOCATION__FK_REQUESTS_REQUEST_LOCATION_REQUEST_ID);

        return _request;
    }

    @Override
    public JRequestLocation as(String alias) {
        return new JRequestLocation(DSL.name(alias), this);
    }

    @Override
    public JRequestLocation as(Name alias) {
        return new JRequestLocation(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JRequestLocation rename(String name) {
        return new JRequestLocation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JRequestLocation rename(Name name) {
        return new JRequestLocation(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
