package org.systems.dipe.srs.test.suites;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.people.PersonDto;
import org.systems.dipe.srs.test.rest.SrsRestClient;
import org.systems.dipe.srs.test.suites.people.PersonProvider;

import java.util.Set;

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
        PersonDto customer = restClient.registerNewPerson(personProvider.buildNew(Set.of("CUSTOMER")));
        PersonDto supervisor = restClient.registerNewPerson(personProvider.buildNew(Set.of("SUPERVISOR")));
        restClient.registerNewPerson(personProvider.buildNew(Set.of("VOLUNTEER")));
        restClient.registerNewPerson(personProvider.buildNew(Set.of("VOLUNTEER")));
        restClient.registerNewPerson(personProvider.buildNew(Set.of("VOLUNTEER")));
    }
}
