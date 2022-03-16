package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;

import java.util.List;

public interface PeopleFacade {

    PersonOutDto create(PersonInDto person);

    List<RoleOutDto> roles();
}
