package org.systems.dipe.srs.request.storage;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.request.RequestItem;
import org.systems.dipe.srs.request.RequestLocation;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class RequestJooqRepository implements RequestRepository {

    private final DefaultDSLContext dsl;

    @Override
    public Request create(Request request) {
        return null;
    }

    @Override
    public Request update(Request request) {
        return null;
    }

    @Override
    public void approve(String requestId) {

    }

    @Override
    public void addItems(Collection<RequestItem> items) {

    }

    @Override
    public void addLocations(Collection<RequestLocation> locations) {

    }
}
