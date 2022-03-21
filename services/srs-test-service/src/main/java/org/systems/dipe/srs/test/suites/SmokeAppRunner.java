package org.systems.dipe.srs.test.suites;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.locations.in.CommentInDto;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.in.PointInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;
import org.systems.dipe.srs.platform.search.out.SearchProcessOutDto;
import org.systems.dipe.srs.platform.squad.out.SquadOutDto;
import org.systems.dipe.srs.test.rest.SrsRestClient;
import org.systems.dipe.srs.test.suites.people.LocationProvider;
import org.systems.dipe.srs.test.suites.people.PersonProvider;
import org.systems.dipe.srs.test.suites.people.RequestProvider;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class SmokeAppRunner implements ApplicationListener<ApplicationReadyEvent> {

    private final SrsRestClient restClient;

    private final PersonProvider personProvider;
    private final RequestProvider requestProvider;
    private final LocationProvider locationProvider;

    @Override
    // TODO refine runner
    // run jobs in cycle, each per logic action
    // remove thread.sleep
    // check high-load and pg stat statements - optimize performance
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Pre-conditions
        //    CUSTOMER,
        //    SUPERVISOR,
        //    VOLUNTEER (2 + 1 lead)
        Map<String, RoleOutDto> rolesMap = restClient.rolesDictionary().stream()
                .collect(Collectors.toMap(RoleOutDto::getRole, Function.identity()));

        PersonOutDto customer = restClient.createPerson(
                personProvider.buildNew(Set.of(rolesMap.get("CUSTOMER").getRoleId()))
        );
        log.debug("Customer was created: \n {}", customer);
        PersonOutDto supervisor = restClient.createPerson(
                personProvider.buildNew(Set.of(rolesMap.get("SUPERVISOR").getRoleId()))
        );
        log.debug("Supervisor was created: \n {}", supervisor);

        LocationInDto locationInDto = locationProvider.buildNewLocation();
        RequestInDto requestInDto = requestProvider.buildNewRequest(
                customer.getPersonId(),
                List.of(personProvider.buildNew(Set.of(rolesMap.get("TARGET").getRoleId()))),
                List.of(locationInDto)
        );
        RequestOutDto request = restClient.submitRequest(requestInDto);
        log.debug("Request was submitted: \n {}", request);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        restClient.assignRequest(request.getRequestId(), supervisor.getPersonId());
        log.debug("Request was assigned");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("Approve items");
        for (String itemId : request.getItemIds()) {
            restClient.approveRequestItem(itemId);
        }

        restClient.approveRequest(request.getRequestId(), supervisor.getPersonId());
        log.debug("Request was approved");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchProcessOutDto search = restClient.search(request.getRequestId());
        log.debug("Search process {} is ready for volunteers", search.getSearchId());

        PersonOutDto volunteer1 = restClient.createPerson(
                personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER").getRoleId()))
        );
        PersonOutDto volunteer2 = restClient.createPerson(
                personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER").getRoleId()))
        );
        PersonOutDto volunteer3 = restClient.createPerson(
                personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER").getRoleId()))
        );

        log.debug("Assign volunteers as members to squad");
        SquadOutDto squad = search.getSquads().iterator().next();
        restClient.joinSquad(search.getSearchId(), squad.getSquadId(), volunteer1.getPersonId());
        restClient.joinSquad(search.getSearchId(), squad.getSquadId(), volunteer2.getPersonId());
        restClient.joinSquad(search.getSearchId(), squad.getSquadId(), volunteer3.getPersonId());

        log.debug("Supervisor confirmation - squad is ready");
        restClient.approveSquad(search.getSearchId(), supervisor.getPersonId());

        PointInDto targetPoint = locationProvider.newPoint();
        restClient.addPoint(targetPoint, search.getSearchId(), volunteer1.getPersonId());
        restClient.addPoint(locationProvider.newPoint(), search.getSearchId(), volunteer2.getPersonId());
        CommentInDto comment = locationProvider.newComment(volunteer3.getPersonId());
        restClient.addComment(comment, search.getSearchId(), targetPoint.getPointId(), volunteer3.getPersonId());

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("Supervisor notification - search is completed");
        restClient.completeSearch(search.getSearchId(), supervisor.getPersonId());
    }
}
