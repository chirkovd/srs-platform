package org.systems.dipe.srs.test.suites.people;

import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.people.PersonDto;

import java.util.Set;

@Component
public class PersonProvider {

    public PersonDto buildNew(Set<String> roles) {
        PersonDto personDto = new PersonDto();
        //TODO generate dto
        return personDto;
    }

}
