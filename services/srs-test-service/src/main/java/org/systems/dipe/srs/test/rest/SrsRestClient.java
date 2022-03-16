package org.systems.dipe.srs.test.rest;

import org.systems.dipe.srs.platform.people.PersonInDto;
import org.systems.dipe.srs.platform.people.RoleDto;

import java.util.List;

public interface SrsRestClient {

    PersonInDto registerNewPerson(PersonInDto person);

    List<RoleDto> rolesDictionary();
}
