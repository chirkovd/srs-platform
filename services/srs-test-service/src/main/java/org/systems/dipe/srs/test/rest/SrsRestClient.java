package org.systems.dipe.srs.test.rest;

import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;

import java.util.List;

public interface SrsRestClient {

    PersonInDto registerNewPerson(PersonInDto person);

    List<RoleOutDto> rolesDictionary();

    RequestOutDto submitRequest(RequestInDto request);
}
