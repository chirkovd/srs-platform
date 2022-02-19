package org.systems.dipe.srs.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.request.storage.RequestRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class RequestClientImpl implements RequestClient {

    private final RequestRepository requestRepository;

    @Override
    public Request create(Request request) {
        return requestRepository.create(request);
    }

    @Override
    public Request update(Request request) {
        return requestRepository.update(request);
    }

    @Override
    public void approve(String requestId) {
        requestRepository.approve(requestId);
    }

    @Override
    public void addItems(Collection<RequestItem> items) {
        requestRepository.addItems(items);
    }

    @Override
    public void addLocations(Collection<RequestLocation> locations) {
        requestRepository.addLocations(locations);
    }
}
