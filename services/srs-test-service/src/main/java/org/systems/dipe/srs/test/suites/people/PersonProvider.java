package org.systems.dipe.srs.test.suites.people;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.people.ContactDto;
import org.systems.dipe.srs.platform.people.IdentificationDto;
import org.systems.dipe.srs.platform.people.PersonInDto;

import java.util.List;
import java.util.Set;

@Component
public class PersonProvider {

    public PersonInDto buildNew(Set<String> roles) {
        PersonInDto personInDto = new PersonInDto();
        personInDto.setFirstName(RandomStringUtils.randomAlphabetic(6));
        personInDto.setLastName(RandomStringUtils.randomAlphabetic(6));
        personInDto.setRoleIds(roles);

        ContactDto contactDto = new ContactDto();
        contactDto.setEmail(RandomStringUtils.randomAlphabetic(6) + "@dipe.com");
        contactDto.setPhone(RandomStringUtils.randomNumeric(9));
        personInDto.setContacts(List.of(contactDto));

        IdentificationDto identificationDto = new IdentificationDto();
        identificationDto.setId(RandomStringUtils.randomNumeric(10));
        identificationDto.setType("passport");
        personInDto.setIdentifications(Set.of(identificationDto));

        return personInDto;
    }

}
