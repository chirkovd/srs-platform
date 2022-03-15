package org.systems.dipe.srs.test.rest;

import org.systems.dipe.srs.platform.people.PersonDto;

public interface SrsRestClient {

    PersonDto registerNewPerson(PersonDto person);

}
