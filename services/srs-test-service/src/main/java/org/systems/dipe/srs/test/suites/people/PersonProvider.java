package org.systems.dipe.srs.test.suites.people;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.people.in.ContactInDto;
import org.systems.dipe.srs.platform.people.in.IdentificationInDto;
import org.systems.dipe.srs.platform.people.in.PersonInDto;

import java.util.List;
import java.util.Set;

@Component
public class PersonProvider {

    public PersonInDto buildNew(Set<String> roles) {
        PersonInDto personInDto = new PersonInDto();
        personInDto.setFirstName(RandomStringUtils.randomAlphabetic(6));
        personInDto.setLastName(RandomStringUtils.randomAlphabetic(6));
        personInDto.setRoleIds(roles);

        ContactInDto contactInDto = new ContactInDto();
        contactInDto.setEmail(RandomStringUtils.randomAlphabetic(6) + "@dipe.com");
        contactInDto.setPhone(RandomStringUtils.randomNumeric(9));
        personInDto.setContacts(List.of(contactInDto));

        IdentificationInDto identificationInDto = new IdentificationInDto();
        identificationInDto.setId(RandomStringUtils.randomNumeric(10));
        identificationInDto.setType("passport");
        personInDto.setIdentifications(Set.of(identificationInDto));

        return personInDto;
    }

}
