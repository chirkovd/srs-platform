package org.systems.dipe.srs.test.suites;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.test.rest.SrsRestClient;
import org.systems.dipe.srs.test.suites.people.LocationProvider;
import org.systems.dipe.srs.test.suites.people.PersonProvider;
import org.systems.dipe.srs.test.suites.people.RequestProvider;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        PersonInDto customer = restClient.registerNewPerson(
                personProvider.buildNew(Set.of(rolesMap.get("CUSTOMER").getRoleId()))
        );
        PersonInDto supervisor = restClient.registerNewPerson(
                personProvider.buildNew(Set.of(rolesMap.get("SUPERVISOR").getRoleId()))
        );

        LocationInDto locationInDto = locationProvider.buildNewLocation();
        RequestInDto requestInDto = requestProvider.buildNewRequest(
                customer.getPersonId(),
                supervisor.getPersonId(),
                List.of(personProvider.buildNew(Set.of(rolesMap.get("TARGET").getRoleId()))),
                List.of(locationInDto)
        );
        restClient.submitRequest(requestInDto);

        restClient.registerNewPerson(personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER").getRoleId())));
        restClient.registerNewPerson(personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER").getRoleId())));
        restClient.registerNewPerson(personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER").getRoleId())));
    }
}
