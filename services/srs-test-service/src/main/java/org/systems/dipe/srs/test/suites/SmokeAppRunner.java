package org.systems.dipe.srs.test.suites;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;
import org.systems.dipe.srs.platform.search.out.SearchProcessOutDto;
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

        restClient.createPerson(personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER").getRoleId())));
        restClient.createPerson(personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER").getRoleId())));
        restClient.createPerson(personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER").getRoleId())));
    }
}
