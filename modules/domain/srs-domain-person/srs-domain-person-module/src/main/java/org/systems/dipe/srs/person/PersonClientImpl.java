package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.contacts.ContactClient;
import org.systems.dipe.srs.person.contacts.ContactSearch;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.identifications.IdentificationClient;
import org.systems.dipe.srs.person.identifications.IdentificationSearch;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RoleClient;
import org.systems.dipe.srs.person.storage.PersonRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PersonClientImpl implements PersonClient {

    private final RoleClient roleClient;
    private final ContactClient contactClient;
    private final IdentificationClient identificationClient;

    private final PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        if (Objects.isNull(person.getPersonId())) {
            person.setPersonId(UUID.randomUUID().toString());
        }
        if (Objects.isNull(person.getCreated())) {
            person.setCreated(ZonedDateTime.now(ZoneId.of("UTC")));
        }

        personRepository.create(person);

        identificationClient.create(person.getIdentifications());
        contactClient.create(person.getContacts());

        if (CollectionUtils.isNotEmpty(person.getRoles())) {
            roleClient.assign(
                    person.getRoles().stream()
                            .map(Role::getRoleId)
                            .collect(Collectors.toSet()),
                    person.getPersonId()
            );
        } else {
            log.error("Roles are missing for person {}", person);
            throw new IllegalArgumentException("Person without role is not valid");
        }

        return getPerson(person.getPersonId());
    }

    @Override
    public Person update(Person person) {
        Objects.requireNonNull(person.getPersonId(), "PersonId should be defined");
        personRepository.update(person);
        identificationClient.update(person.getIdentifications());
        contactClient.update(person.getContacts());

        if (CollectionUtils.isEmpty(person.getRoles())) {
            log.error("Roles are missing for person {}", person);
            throw new IllegalArgumentException("Person without role is not valid");
        }
        roleClient.assign(
                person.getRoles().stream()
                        .map(Role::getRoleId)
                        .collect(Collectors.toSet()),
                person.getPersonId()
        );

        return getPerson(person.getPersonId());
    }

    @Override
    public Collection<Person> find(PersonSearch search) {
        if (CollectionUtils.isEmpty(search.getPersonIds())) {
            return Collections.emptyList();
        }
        Collection<Person> people = personRepository.find(search);

        if (!people.isEmpty() && search.isWithDetails()) {
            Map<String, Person> personMap = people.stream().collect(
                    Collectors.toMap(Person::getPersonId, Function.identity()));

            loadIdentifications(personMap);
            loadContacts(personMap);
            loadRoles(personMap);
        }

        return people;
    }

    private Person getPerson(String personId) {
        Collection<Person> people = find(
                PersonSearch.builder()
                        .personIds(Set.of(personId))
                        .withDetails(true)
                        .build()
        );
        if (!people.isEmpty()) {
            return people.iterator().next();
        } else {
            log.error("Cannot find new person by id {}", personId);
            throw new IllegalStateException("Person was not stored");
        }
    }

    private void loadRoles(Map<String, Person> personMap) {
        Map<String, Set<Role>> roleMap = roleClient.fetchAssignment(personMap.keySet());
        if (MapUtils.isNotEmpty(roleMap)) {
            roleMap.forEach((personId, roles) -> {
                Person person = personMap.get(personId);
                person.setRoles(roles);
            });
        }
    }

    private void loadContacts(Map<String, Person> personMap) {
        Collection<Contact> contacts = contactClient.search(
                ContactSearch.builder()
                        .personIds(personMap.keySet())
                        .build()
        );
        if (CollectionUtils.isNotEmpty(contacts)) {
            for (Contact contact : contacts) {
                Person person = personMap.get(contact.getPersonId());
                if (Objects.isNull(person.getContacts())) {
                    person.setContacts(new ArrayList<>());
                }
                person.getContacts().add(contact);
            }
        }
    }

    private void loadIdentifications(Map<String, Person> personMap) {
        Collection<Identification> identifications = identificationClient.search(
                IdentificationSearch.builder()
                        .personIds(personMap.keySet())
                        .build()
        );
        if (CollectionUtils.isNotEmpty(identifications)) {
            for (Identification identification : identifications) {
                Person person = personMap.get(identification.getPersonId());
                if (Objects.isNull(person.getIdentifications())) {
                    person.setIdentifications(new ArrayList<>());
                }
                person.getIdentifications().add(identification);
            }
        }
    }
}
