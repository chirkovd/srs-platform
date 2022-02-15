package org.systems.dipe.srs.request;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RequestClientImpl implements RequestClient {

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
    public void addLocation(RequestLocation location) {

    }
}
