package org.systems.dipe.srs.test.rest;

import org.systems.dipe.srs.platform.locations.in.CommentInDto;
import org.systems.dipe.srs.platform.locations.in.PointInDto;
import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;
import org.systems.dipe.srs.platform.search.out.SearchProcessOutDto;

import java.util.List;

public interface SrsRestClient {

    PersonOutDto createPerson(PersonInDto person);

    List<RoleOutDto> rolesDictionary();

    RequestOutDto submitRequest(RequestInDto request);

    RequestOutDto findRequest(String requestId);

    void assignRequest(String requestId, String supervisorId);

    void approveRequest(String requestId, String supervisorId);

    void approveRequestItem(String requestItemId);

    SearchProcessOutDto search(String requestId);

    void joinSquad(String searchId, String squadId, String volunteerId);

    void approveSquad(String searchId, String supervisorId);

    void addPoint(PointInDto point, String searchId, String volunteerId);

    void addComment(CommentInDto comment, String searchId, String pointId, String volunteerId);

    void completeSearch(String searchId, String supervisorId);

}
