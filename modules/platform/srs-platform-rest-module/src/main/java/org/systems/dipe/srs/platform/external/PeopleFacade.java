package org.systems.dipe.srs.platform.external;

import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;

import java.util.List;
import java.util.Set;

public interface PeopleFacade {

    PersonOutDto create(PersonInDto person);

    List<PersonOutDto> search(Set<String> personIds);

    List<RoleOutDto> roles();
}
