package org.systems.dipe.srs.person.storage;

import org.systems.dipe.srs.person.Person;

public interface PersonRepository {
    Person create(Person person);
}
