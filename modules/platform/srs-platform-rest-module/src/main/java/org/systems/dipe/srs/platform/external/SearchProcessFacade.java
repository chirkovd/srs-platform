package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.locations.in.CommentInDto;
import org.systems.dipe.srs.platform.locations.in.PointInDto;
import org.systems.dipe.srs.platform.search.SearchProcessRequest;
import org.systems.dipe.srs.platform.search.out.SearchProcessOutDto;

public interface SearchProcessFacade {
    SearchProcessOutDto search(SearchProcessRequest request);

    void joinSquad(String searchId, String squadId, String volunteerId);

    void approveSquad(String searchId, String supervisorId);

    void addPoint(PointInDto point, String searchId, String volunteerId);

    void addComment(CommentInDto comment, String searchId, String pointId, String volunteerId);

    void completeSearch(String searchId, String supervisorId);
}
