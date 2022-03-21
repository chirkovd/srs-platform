package org.systems.dipe.srs.platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.systems.dipe.srs.platform.external.SearchProcessFacade;
import org.systems.dipe.srs.platform.locations.in.CommentInDto;
import org.systems.dipe.srs.platform.locations.in.PointInDto;
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

    @PutMapping("/api/search-process/{searchId}/squad/{squadId}/member")
    public void joinSquad(
            @PathVariable String searchId,
            @PathVariable String squadId,
            @RequestParam String volunteerId
    ) {
        searchProcessFacade.joinSquad(searchId, squadId, volunteerId);
    }

    @PutMapping("/api/search-process/{searchId}/approve-squad")
    public void approveSquad(@PathVariable String searchId, @RequestParam String supervisorId) {
        searchProcessFacade.approveSquad(searchId, supervisorId);
    }

    @PostMapping("/api/search-process/{searchId}/point")
    public void addPoint(
            @RequestBody PointInDto point,
            @PathVariable String searchId,
            @RequestParam String volunteerId
    ) {
        searchProcessFacade.addPoint(point, searchId, volunteerId);
    }

    @PostMapping("/api/search-process/{searchId}/point/{pointId}/comment")
    public void addComment(
            @RequestBody CommentInDto comment,
            @PathVariable String searchId,
            @PathVariable String pointId,
            @RequestParam String volunteerId
    ) {
        searchProcessFacade.addComment(comment, searchId, pointId, volunteerId);
    }

    @PutMapping("/api/search-process/{searchId}/complete")
    public void completeSearch(@PathVariable String searchId, @RequestParam String supervisorId) {
        searchProcessFacade.completeSearch(searchId, supervisorId);
    }
}
