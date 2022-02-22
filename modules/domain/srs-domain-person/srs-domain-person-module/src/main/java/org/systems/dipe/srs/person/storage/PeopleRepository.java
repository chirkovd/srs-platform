package org.systems.dipe.srs.person.storage;

import org.systems.dipe.srs.person.PeopleSearch;
import org.systems.dipe.srs.person.Person;

import java.util.Collection;

public interface PeopleRepository {

    void create(Person person);

    void update(Person person);

    Collection<Person> find(PeopleSearch search);
}
