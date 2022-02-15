package org.systems.dipe.srs.person.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.systems.dipe.srs.person.roles.RoleClient;

import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonConfig {

    @ComponentScan(basePackages = "org.systems.dipe.srs.person")
    public static class Module {

        @Bean
        public PersonRolesConfig personRoles(RoleClient roleClient) {
            return new PersonRolesConfig(roleClient);
        }

    }

    @AllArgsConstructor
    private static class PersonRolesConfig {

        private final RoleClient roleClient;

        @EventListener(classes = ApplicationReadyEvent.class)
        public void onEvent(ApplicationReadyEvent event) {
            roleClient.create(Collections.emptyList());
        }
    }

}
