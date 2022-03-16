package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.person.PeopleClient;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.roles.RolesClient;
import org.systems.dipe.srs.platform.external.PeopleFacade;
import org.systems.dipe.srs.platform.mappers.PeopleDtoMapper;
import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;
import org.systems.dipe.srs.utils.GroupUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@ConditionalOnBean(PeopleClient.class)
public class PeopleFacadeImpl implements PeopleFacade {

    private final PeopleClient peopleClient;
    private final RolesClient rolesClient;
    private final PeopleDtoMapper mapper;

    @Override
    public PersonOutDto create(PersonInDto personIn) {
        Person person = peopleClient.create(mapper.fromInDto(personIn));

        PersonOutDto personOut = mapper.toOutDto(person);

        Map<String, RoleOutDto> roleMap = GroupUtils.groupBy(roles(), RoleOutDto::getRoleId);
        personOut.setRoles(person.getRoleIds().stream().map(roleMap::get).collect(Collectors.toSet()));

        return personOut;
    }

    @Override
    public List<RoleOutDto> roles() {
        return rolesClient.all().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
