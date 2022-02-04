package org.systems.dipe.srs.request;

public class RequestServiceImpl implements RequestService {

    @Override
    public Request create() {
        return new Request("hello");
    }
}
