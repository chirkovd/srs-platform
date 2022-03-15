package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.person.PeopleClient;
import org.systems.dipe.srs.person.roles.RolesClient;
import org.systems.dipe.srs.platform.external.PeopleFacade;
import org.systems.dipe.srs.platform.mappers.PeopleDtoMapper;
import org.systems.dipe.srs.platform.people.PersonDto;
import org.systems.dipe.srs.platform.people.RoleDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@ConditionalOnBean(PeopleClient.class)
public class PeopleFacadeImpl implements PeopleFacade {

    private final PeopleClient peopleClient;
    private final RolesClient rolesClient;
    private final PeopleDtoMapper mapper;

    @Override
    public PersonDto create(PersonDto person) {
        return mapper.toDto(
                peopleClient.create(
                        mapper.fromDto(person)
                )
        );
    }

    @Override
    public List<RoleDto> roles() {
        return rolesClient.all().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
