package org.systems.dipe.srs.person;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsDbTest;
import org.systems.dipe.srs.person.config.TestConfig;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RoleClient;

import java.util.Collection;

@SpringBootTest(classes = TestConfig.class)
class RoleClientImplTest extends SrsDbTest {

    @Autowired
    private RoleClient roleClient;

    @Test
    void create() {
        Collection<Role> roles = roleClient.all();
        Assertions.assertThat(roles).isNotEmpty();
    }
}