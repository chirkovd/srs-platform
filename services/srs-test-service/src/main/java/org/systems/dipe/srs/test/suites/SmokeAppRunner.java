package org.systems.dipe.srs.test.suites;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.people.PersonDto;
import org.systems.dipe.srs.platform.people.RoleDto;
import org.systems.dipe.srs.test.rest.SrsRestClient;
import org.systems.dipe.srs.test.suites.people.PersonProvider;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SmokeAppRunner implements ApplicationListener<ApplicationReadyEvent> {

    private final SrsRestClient restClient;

    private final PersonProvider personProvider;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Pre-conditions
        //    CUSTOMER,
        //    SUPERVISOR,
        //    VOLUNTEER (2 + 1 lead)
        Map<String, RoleDto> rolesMap = restClient.rolesDictionary().stream()
                .collect(Collectors.toMap(RoleDto::getRole, Function.identity()));

        PersonDto customer = restClient.registerNewPerson(
                personProvider.buildNew(Set.of(rolesMap.get("CUSTOMER")))
        );
        PersonDto supervisor = restClient.registerNewPerson(
                personProvider.buildNew(Set.of(rolesMap.get("SUPERVISOR")))
        );
        restClient.registerNewPerson(personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER"))));
        restClient.registerNewPerson(personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER"))));
        restClient.registerNewPerson(personProvider.buildNew(Set.of(rolesMap.get("VOLUNTEER"))));
    }
}
