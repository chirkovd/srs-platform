package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.people.PersonDto;
import org.systems.dipe.srs.platform.people.RoleDto;

import java.util.List;

public interface PeopleFacade {

    PersonDto create(PersonDto person);

    List<RoleDto> roles();
}
