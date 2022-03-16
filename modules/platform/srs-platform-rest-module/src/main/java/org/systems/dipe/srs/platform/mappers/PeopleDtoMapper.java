package org.systems.dipe.srs.platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.platform.people.in.ContactInDto;
import org.systems.dipe.srs.platform.people.in.IdentificationInDto;
import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.people.out.RoleOutDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PeopleDtoMapper extends CommonMapper {

    @Mapping(target = "roles", ignore = true)
    PersonOutDto toOutDto(Person person);

    Person fromInDto(PersonInDto personInDto);

    @Mapping(target = "personId", ignore = true)
    Contact fromInContactDto(ContactInDto contactInDto);

    @Mapping(target = "personId", ignore = true)
    Identification fromInIdentificationDto(IdentificationInDto identificationInDto);

    RoleOutDto toDto(Role role);

}
