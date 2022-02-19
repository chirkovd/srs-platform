package org.systems.dipe.srs.person.storage;

import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.PersonSearch;

import java.util.Collection;

public interface PersonRepository {

    void create(Person person);

    void update(Person person);

    Collection<Person> find(PersonSearch search);
}
