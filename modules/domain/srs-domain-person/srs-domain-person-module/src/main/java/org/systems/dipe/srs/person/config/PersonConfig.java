package org.systems.dipe.srs.person.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RoleClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonConfig {

    @Import(PersonFlywayConfig.class)
    @ComponentScan(basePackages = "org.systems.dipe.srs.person")
    public static class Module {

        @Bean
        public PersonRolesConfig personRoles(
                @Value("classpath:srs-roles.json") Resource resource,
                RoleClient roleClient, ObjectMapper objectMapper
        ) {
            return new PersonRolesConfig(roleClient, resource, objectMapper);
        }

    }

    @Slf4j
    @AllArgsConstructor
    private static class PersonRolesConfig {

        private final RoleClient roleClient;
        private final Resource resource;
        private final ObjectMapper objectMapper;

        @EventListener(classes = ApplicationReadyEvent.class)
        public void onEvent(ApplicationReadyEvent event) {
            if (CollectionUtils.isNotEmpty(roleClient.all())) {
                return;
            }
            try (InputStream inputStream = resource.getInputStream()) {
                List<Role> roles = objectMapper.readValue(
                        inputStream, new TypeReference<>() {
                        });
                roleClient.create(roles);
            } catch (IOException e) {
                log.error("Cannot upload roles", e);
            }
        }
    }

}
