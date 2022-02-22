package org.systems.dipe.srs.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsDbTest;
import org.systems.dipe.srs.person.config.TestConfig;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RoleAlias;
import org.systems.dipe.srs.person.roles.RolesClient;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootTest(classes = TestConfig.class)
class PeopleClientTest extends SrsDbTest {

    @Autowired
    private PeopleClient peopleClient;
    @Autowired
    private RolesClient rolesClient;

    private Map<RoleAlias, Role> roles;

    @BeforeEach
    void setUp() {
        roles = rolesClient.all().stream().collect(
                Collectors.toMap(Role::getRole, Function.identity())
        );
    }

    @Test
    void create() {
        Person person = new Person();
        person.setPersonId(UUID.randomUUID().toString());
        person.setFirstName("FirstName");
        person.setLastName("LastName");

        Contact contact = new Contact();
        contact.setPersonId(person.getPersonId());
        contact.setEmail("person@email.com");
        contact.setPhone("+7-999-99-99");
        person.setContacts(Set.of(contact));

        Identification identification = new Identification();
        identification.setPersonId(person.getPersonId());
        identification.setId("9040 030303");
        identification.setType("passport");
        person.setIdentifications(Set.of(identification));

        person.setRoles(List.of(
                roles.get(RoleAlias.CUSTOMER),
                roles.get(RoleAlias.VOLUNTEER)
        ));

        Person result = peopleClient.create(person);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getFirstName(), person.getFirstName());
        Assertions.assertEquals(result.getLastName(), person.getLastName());
        Assertions.assertNotNull(result.getCreated());
        Assertions.assertNotNull(result.getIdentifications());
        Assertions.assertNotNull(result.getContacts());
        Assertions.assertNotNull(result.getRoles());
    }

    @Test
    void updatePerson() {
        Person person = new Person();
        person.setPersonId(UUID.randomUUID().toString());
        person.setFirstName("FirstName");
        person.setLastName("LastName");

        person.setRoles(List.of(
                roles.get(RoleAlias.TARGET)
        ));

        Person result = peopleClient.create(person);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getFirstName(), "FirstName");

        person.setFirstName("FirstNameNew");
        result = peopleClient.update(person);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getFirstName(), "FirstNameNew");
    }
}