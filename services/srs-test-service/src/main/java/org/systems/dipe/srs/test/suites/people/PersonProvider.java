package org.systems.dipe.srs.test.suites.people;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.people.ContactDto;
import org.systems.dipe.srs.platform.people.IdentificationDto;
import org.systems.dipe.srs.platform.people.PersonDto;
import org.systems.dipe.srs.platform.people.RoleDto;

import java.util.List;
import java.util.Set;

@Component
public class PersonProvider {

    public PersonDto buildNew(Set<RoleDto> roles) {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName(RandomStringUtils.randomAlphabetic(6));
        personDto.setLastName(RandomStringUtils.randomAlphabetic(6));
        personDto.setRoles(roles);
        ContactDto contactDto = new ContactDto();
        contactDto.setEmail(RandomStringUtils.randomAlphabetic(6) + "@dipe.com");
        contactDto.setPhone(RandomStringUtils.randomNumeric(9));
        personDto.setContacts(List.of(contactDto));
        IdentificationDto identificationDto = new IdentificationDto();
        identificationDto.setId(RandomStringUtils.randomNumeric(10));
        identificationDto.setType("passport");
        personDto.setIdentifications(List.of(identificationDto));
        return personDto;
    }

}
