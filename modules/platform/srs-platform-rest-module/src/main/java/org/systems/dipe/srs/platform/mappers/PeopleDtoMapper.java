package org.systems.dipe.srs.platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.platform.people.PersonDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PeopleDtoMapper extends CommonMapper {

    PersonDto toDto(Person person);

    Person fromDto(PersonDto personDto);

}
