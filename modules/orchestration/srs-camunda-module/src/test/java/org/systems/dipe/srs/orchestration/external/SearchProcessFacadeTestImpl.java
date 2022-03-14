package org.systems.dipe.srs.orchestration.external;

import lombok.Getter;
import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchProcessStatus;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SearchProcessFacadeTestImpl implements SearchProcessFacade {

    @Getter
    private final Map<String, String> requests = new HashMap<>();

    @Override
    public SearchProcess create(SearchProcess process) {
        String searchId = UuidUtils.newStr();
        process.setSearchId(searchId);
        requests.put(process.getRequestId(), searchId);
        return process;
    }

    @Override
    public SearchProcess update(SearchProcess process) {
        return process;
    }

    @Override
    public SearchProcess find(String searchId) {
        SearchProcess searchProcess = new SearchProcess();
        searchProcess.setSearchId(searchId);
        requests.forEach((requestId, id) -> {
            if (Objects.equals(id, searchId)) {
                searchProcess.setRequestId(requestId);
            }
        });
        return searchProcess;
    }

    @Override
    public void updateStatus(String searchId, SearchProcessStatus status) {
        // ignore
    }
}
