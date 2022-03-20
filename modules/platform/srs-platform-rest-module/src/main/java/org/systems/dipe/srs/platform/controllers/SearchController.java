package org.systems.dipe.srs.platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.systems.dipe.srs.platform.external.SearchProcessFacade;
import org.systems.dipe.srs.platform.search.SearchProcessRequest;
import org.systems.dipe.srs.platform.search.out.SearchProcessOutDto;

@RestController
@AllArgsConstructor
public class SearchController {

    private final SearchProcessFacade searchProcessFacade;

    @PostMapping("/api/search-process/search")
    public SearchProcessOutDto search(@RequestBody SearchProcessRequest request) {
        return searchProcessFacade.search(request);
    }

}
