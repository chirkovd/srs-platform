package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.person.PeopleClient;
import org.systems.dipe.srs.platform.external.PeopleFacade;
import org.systems.dipe.srs.platform.mappers.PeopleDtoMapper;
import org.systems.dipe.srs.platform.people.PersonDto;

@Component
@AllArgsConstructor
@ConditionalOnBean(PeopleClient.class)
public class PeopleFacadeImpl implements PeopleFacade {

    private final PeopleClient client;
    private final PeopleDtoMapper mapper;

    @Override
    public PersonDto create(PersonDto person) {
        return mapper.toDto(client.create(mapper.fromDto(person)));
    }
}
