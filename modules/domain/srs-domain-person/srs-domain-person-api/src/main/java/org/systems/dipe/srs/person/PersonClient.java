package org.systems.dipe.srs.person;

import java.util.Collection;

public interface PersonClient {

    Person create(Person person);

    Person update(Person person);

    Collection<Person> find(PersonSearch search);

}
