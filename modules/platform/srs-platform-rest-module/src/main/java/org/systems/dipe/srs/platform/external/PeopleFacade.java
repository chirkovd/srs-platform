package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.people.PersonInDto;
import org.systems.dipe.srs.platform.people.PersonOutDto;
import org.systems.dipe.srs.platform.people.RoleDto;

import java.util.List;

public interface PeopleFacade {

    PersonOutDto create(PersonInDto person);

    List<RoleDto> roles();
}
