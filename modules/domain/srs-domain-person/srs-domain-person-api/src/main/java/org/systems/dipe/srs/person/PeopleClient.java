package org.systems.dipe.srs.person;

import java.util.Collection;

public interface PeopleClient {

    Person create(Person person);

    Person update(Person person);

    Collection<Person> find(PeopleSearch search);
}
