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
import org.systems.dipe.srs.person.roles.RoleClient;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootTest(classes = TestConfig.class)
class PersonClientTest extends SrsDbTest {

    @Autowired
    private PersonClient personClient;
    @Autowired
    private RoleClient roleClient;

    private Map<RoleAlias, Role> roles;

    @BeforeEach
    void setUp() {
        roles = roleClient.all().stream().collect(
                Collectors.toMap(Role::getRole, Function.identity()));
    }

    @Test
    void create() {
        Person person = new Person();
        person.setFirstName("FirstName");
        person.setLastName("LastName");

        Contact contact = new Contact();
        contact.setEmail("person@email.com");
        contact.setPhone("+7-999-99-99");
        person.setContacts(Set.of(contact));

        Identification identification = new Identification();
        identification.setId("9040 030303");
        identification.setType("passport");
        person.setIdentifications(Set.of(identification));

        person.setRoles(List.of(
                roles.get(RoleAlias.CUSTOMER),
                roles.get(RoleAlias.VOLUNTEER)
        ));

        Person result = personClient.create(person);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getFirstName(), person.getFirstName());
        Assertions.assertEquals(result.getLastName(), person.getLastName());
        Assertions.assertNotNull(result.getCreated());
        Assertions.assertNotNull(result.getIdentifications());
        Assertions.assertNotNull(result.getContacts());
        Assertions.assertNotNull(result.getRoles());
    }

    @Test
    void update() {
    }

    @Test
    void find() {
    }
}