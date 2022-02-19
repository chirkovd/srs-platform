/*
 * This file is generated by jOOQ.
 */
package org.systems.dipe.srs.search.jooq;


import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;
import org.systems.dipe.srs.search.jooq.tables.JSearchLocation;
import org.systems.dipe.srs.search.jooq.tables.JSearchProcess;
import org.systems.dipe.srs.search.jooq.tables.JSearchSquad;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JSearches extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>searches</code>
     */
    public static final JSearches SEARCHES = new JSearches();

    /**
     * The table <code>searches.search_location</code>.
     */
    public final JSearchLocation SEARCH_LOCATION = JSearchLocation.SEARCH_LOCATION;

    /**
     * The table <code>searches.search_process</code>.
     */
    public final JSearchProcess SEARCH_PROCESS = JSearchProcess.SEARCH_PROCESS;

    /**
     * The table <code>searches.search_squad</code>.
     */
    public final JSearchSquad SEARCH_SQUAD = JSearchSquad.SEARCH_SQUAD;

    /**
     * No further instances allowed
     */
    private JSearches() {
        super("searches", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            JSearchLocation.SEARCH_LOCATION,
            JSearchProcess.SEARCH_PROCESS,
            JSearchSquad.SEARCH_SQUAD
        );
    }
}