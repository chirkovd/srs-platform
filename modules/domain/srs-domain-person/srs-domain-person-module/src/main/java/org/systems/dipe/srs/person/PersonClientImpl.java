package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.person.storage.PersonRepository;

import java.util.Collection;


@Service
@AllArgsConstructor
public class PersonClientImpl implements PersonClient {

    private final PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        return personRepository.create(person);
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public Collection<Person> find(PersonSearch search) {
        return null;
    }
}
