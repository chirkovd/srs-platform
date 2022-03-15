package org.systems.dipe.srs.test.rest;

import org.systems.dipe.srs.platform.people.PersonDto;
import org.systems.dipe.srs.platform.people.RoleDto;

import java.util.List;

public interface SrsRestClient {

    PersonDto registerNewPerson(PersonDto person);

    List<RoleDto> rolesDictionary();
}
