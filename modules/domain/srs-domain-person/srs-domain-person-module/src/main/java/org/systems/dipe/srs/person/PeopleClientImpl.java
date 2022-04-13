package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.metrics.SrsMetric;
import org.systems.dipe.metrics.SrsMetricHolder;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.contacts.ContactsClient;
import org.systems.dipe.srs.person.contacts.ContactsSearch;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.identifications.IdentificationsClient;
import org.systems.dipe.srs.person.identifications.IdentificationsSearch;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RolesClient;
import org.systems.dipe.srs.person.storage.PeopleRepository;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.*;

@Slf4j
@Service
@Transactional
@SrsMetric("person")
@AllArgsConstructor
public class PeopleClientImpl implements PeopleClient, SrsMetricHolder {

    private final RolesClient rolesClient;
    private final ContactsClient contactsClient;
    private final IdentificationsClient identificationsClient;

    private final PeopleRepository peopleRepository;

    @Override
    public Person create(Person person) {
        if (Objects.isNull(person.getPersonId())) {
            person.setPersonId(UuidUtils.newStr());
        }
        if (Objects.isNull(person.getCreated())) {
            person.setCreated(TimeUtils.now());
        }

        peopleRepository.create(person);

        if (CollectionUtils.isNotEmpty(person.getIdentifications())) {
            for (Identification identification : person.getIdentifications()) {
                identification.setPersonId(person.getPersonId());
            }
        }
        identificationsClient.create(person.getIdentifications());

        if (CollectionUtils.isNotEmpty(person.getContacts())) {
            for (Contact contact : person.getContacts()) {
                contact.setPersonId(person.getPersonId());
            }
        }
        contactsClient.create(person.getContacts());

        if (CollectionUtils.isNotEmpty(person.getRoleIds())) {
            rolesClient.assign(person.getRoleIds(), person.getPersonId());
        } else {
            log.error("Roles are missing for person {}", person);
            throw new IllegalArgumentException("Person without role is not valid");
        }

        return getPerson(person.getPersonId());
    }

    @Override
    public Person update(Person person) {
        Objects.requireNonNull(person.getPersonId(), "PersonId should be defined");
        peopleRepository.update(person);
        identificationsClient.update(person.getIdentifications());
        contactsClient.update(person.getContacts());

        if (CollectionUtils.isEmpty(person.getRoleIds())) {
            log.error("Roles are missing for person {}", person);
            throw new IllegalArgumentException("Person without role is not valid");
        }
        rolesClient.assign(person.getRoleIds(), person.getPersonId());

        return getPerson(person.getPersonId());
    }

    @Override
    public Collection<Person> find(PeopleSearch search) {
        if (CollectionUtils.isEmpty(search.getPersonIds())) {
            return Collections.emptyList();
        }
        Collection<Person> people = peopleRepository.find(search);

        if (!people.isEmpty() && search.isWithDetails()) {
            Map<String, Person> personMap = GroupUtils.groupBy(people, Person::getPersonId);

            loadIdentifications(personMap);
            loadContacts(personMap);
            loadRoles(personMap);
        }

        return people;
    }

    private Person getPerson(String personId) {
        Collection<Person> people = find(
                PeopleSearch.builder()
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
        Map<String, Set<Role>> roleMap = rolesClient.fetchAssignment(personMap.keySet());
        if (MapUtils.isNotEmpty(roleMap)) {
            roleMap.forEach((personId, roles) -> {
                Person person = personMap.get(personId);
                person.setRoleIds(GroupUtils.extractUnique(roles, Role::getRoleId));
            });
        }
    }

    private void loadContacts(Map<String, Person> personMap) {
        Collection<Contact> contacts = contactsClient.search(
                ContactsSearch.builder()
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
        Collection<Identification> identifications = identificationsClient.search(
                IdentificationsSearch.builder()
                        .personIds(personMap.keySet())
                        .build()
        );
        if (CollectionUtils.isNotEmpty(identifications)) {
            for (Identification identification : identifications) {
                Person person = personMap.get(identification.getPersonId());
                if (Objects.isNull(person.getIdentifications())) {
                    person.setIdentifications(new HashSet<>());
                }
                person.getIdentifications().add(identification);
            }
        }
    }

    @Override
    public double initMetric() {
        return peopleRepository.count();
    }
}
