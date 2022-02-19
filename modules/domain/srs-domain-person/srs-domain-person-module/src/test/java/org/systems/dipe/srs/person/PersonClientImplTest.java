package org.systems.dipe.srs.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.person.config.TestConfig;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.roles.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest(classes = TestConfig.class)
class PersonClientImplTest {

    @Autowired
    private PersonClient personClient;

    private Map<String, Role> roles;

    @BeforeEach
    void setUp() {
        //TODO create roles
        roles = new HashMap<>();
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
                roles.get("CUSTOMER"),
                roles.get("VOLUNTEER")
        ));

        Person result = personClient.create(person);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getFirstName(), person.getFirstName());
        Assertions.assertEquals(result.getLastName(), person.getLastName());
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