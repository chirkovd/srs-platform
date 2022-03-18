package org.systems.dipe.srs.orchestration.external;

import lombok.Getter;
import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.request.RequestLocation;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RequestsFacadeTestImpl implements RequestsFacade {

    @Getter
    private final Set<String> requests = new HashSet<>();
    @Getter
    private final Set<String> supervisors = new HashSet<>();

    @Override
    public void assign(String requestId, String supervisorId) {
        supervisors.add(supervisorId);
    }

    @Override
    public void approve(String requestId, String supervisorId) {
        requests.add(requestId);
    }

    @Override
    public Request find(String requestId) {
        Request request = new Request();
        request.setRequestId(requestId);
        RequestLocation requestLocation = new RequestLocation();
        requestLocation.setLocationId(UuidUtils.newStr());
        request.setLocations(List.of(requestLocation));
        return request;
    }
}
